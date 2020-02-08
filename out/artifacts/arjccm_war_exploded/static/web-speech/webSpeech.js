/**
 * 语音合成,文本转语音
 */

var synth = window.speechSynthesis;
var inputTxt = document.querySelector('.txt');
var pitchValue = 1;//声音粗细0-2
var rateValue = 0.5;//声音速率0.5-2
var voices = [];//语言种类
$(function(){
	if(!isIE()){
		voices = synth.getVoices();
	}else{
	}

})
function speak(txt){
    if (synth.speaking) {
        console.error('speechSynthesis.speaking');
        return;
    }
    if (txt !== '') {
    var utterThis = new SpeechSynthesisUtterance(txt);
    utterThis.onend = function (event) {
        console.log('SpeechSynthesisUtterance.onend');
    }
    utterThis.onerror = function (event) {
        console.error('SpeechSynthesisUtterance.onerror');
    }
    utterThis.voice = voices[0];
    utterThis.pitch = pitchValue;
    utterThis.rate = rateValue;
    synth.speak(utterThis);
  }
}
function isIE() {
	 if (!!window.ActiveXObject || "ActiveXObject" in window){
		  return true;
	 }else{
		  return false; 
	  }
	  
	 }