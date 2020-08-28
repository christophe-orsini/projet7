package com.ocdev.biblio.batch.services;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ocdev.biblio.batch.model.Pret;
import com.ocdev.biblio.batch.model.Utilisateur;

@Service
public class EmailServiceImpl implements EmailService
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired DelayEmailContentBuilder contentBuilder;
	
	@Override
    public void envoiEmailEnRetard(Utilisateur abonne) throws MessagingException
	{
		JavaMailSender emailSender = getJavaMailSender();
				
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(abonne.getEmail());
        helper.setText(contentBuilder.build(abonne), true);
        helper.setSubject("Votre retour de prêt est en retard");
        helper.setFrom("info@biblio.fr");
        emailSender.send(message);
    }
	
	private JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost(properties.host());
		mailSenderImpl.setPort(properties.port());
		mailSenderImpl.setUsername(properties.username());
		mailSenderImpl.setPassword(properties.emailPassword());
		return mailSenderImpl;
	}

	@Override
	public Collection<Utilisateur> pretsParAbonne(Collection<Pret> prets)
	{
		if (prets == null || prets.isEmpty())
		{
			return new ArrayList<Utilisateur>();
		}
		
		Map<String, Utilisateur> utilisateurs = new HashMap<String, Utilisateur>();
		for (Pret pret : prets)
		{
			if (utilisateurs.containsKey(pret.getAbonne().getNom()))
			{
				// update
				utilisateurs.get(pret.getAbonne().getNom()).getPrets().add(pret);
			}
			else
			{
				// add
				Utilisateur utilisateur = new Utilisateur(pret.getAbonne().getEmail(), null,
						pret.getAbonne().getNom(), 
						pret.getAbonne().getPrenom());
				utilisateur.setPrets(new ArrayList<Pret>());
				utilisateur.getPrets().add(pret);
				utilisateurs.put(pret.getAbonne().getNom(), utilisateur);
			}
		}
		
		return utilisateurs.values();
	}
}
