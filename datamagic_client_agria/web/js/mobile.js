/* Return TRUE if user is using mobile phone browser */ 
function ismobile(){ 
	$useragent = $_SERVER['HTTP_USER_AGENT']; 
	// Standard vendor-model/version user agents
if (preg_match('/^((ACERŚAlcatelŚAUDIOVOXŚBlackBerryŚCDMŚEricssonŚLG\bŚLGEŚMotorolaŚMOTŚNECŚNokiaŚPanasonicŚQCIŚSAGEMŚSAMSUNGŚSECŚSanyoŚSendoŚSHARPŚSIEŚSonyEricssonŚAppleŚTelitŚTelit_mobile_TerminalsŚTSM)[- ]?([^\/\s\_]+))(\/(\S+))?/', $useragent)){ 
return true; 
}else{ 
return false; 
} 
}

function renderedfalse(){
	if(ismobile())
		return false;
}