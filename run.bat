@echo off
chcp 65001
cls
if not exist target\escalade-1.0.2.war (
	echo **************************************************************************************
	echo * L'application n'est pas installée !                                                *
	echo * Exécuter la commande 'install' en mode console ou double-cliquer sur 'install.bat' *
	echo * pour installer l'application                                                       *
	echo **************************************************************************************
) else (
	java -jar target\escalade-1.0.2.war
)