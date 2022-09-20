package objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class ShintoPriest extends GameObject{
	private int talk = 0;

	public ShintoPriest(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		try {
			normalLook[0] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/ShintoPriest/front.png")), "ShintoPriestFront", Color.pink);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void additionalStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickAction(String action) {
		// TODO Auto-generated method stub
		if(action.equals("SPACE")){
			switch(talk){
			case 0:
				parent.getPlayer().setTalking(true);
				parent.setTextCustom("おい、待て！#NEXT#ここで何をしてるの？", Color.black);
				talk++;
				break;
			case 1:
				parent.setTextCustom("わしの名は...#NEXT#いや、どうでもいいな。",Color.black);
				talk++;
				break;
			case 2:
				parent.setTextCustom("あんたは...あの...#NEXT#「"+parent.getPlayer().getName()+"」と申しますね。",Color.black);
				talk++;
				break;
			case 3:
				parent.setTextCustom("素敵な名前だろう！#NEXT#気に入るぞ。",Color.black);
				talk++;
				break;
			case 4:
				parent.setTextCustom("日本語でその名前を書けるか？",Color.black);
				talk++;
				break;
			case 5:
				parent.setTextCustom("「"+parent.getPlayer().getName()+"」は日本語で#NEXT#「"+testConvert(getPrepared(parent.getPlayer().getName()))+"」だと思うぞ。",Color.black);
				talk++;
				break;
			case 6:
				parent.setTextCustom("でも、間違っているなら、すみません。#NEXT#じゃ、またね！",Color.black);
				talk++;
				break;
			case 7:
				parent.setTextCustom("", Color.black);
				parent.getPlayer().setTalking(false);
				talk++;
			default:
					
			}
		}
	}
	public static String getPrepared(String work){
		work = work.toLowerCase();
		work = preWork(work);
		char text[] = work.toCharArray();
		work = "";
		//Pruefe gesamten, gegebenen Text...
		for(int i = 0; i < text.length; i++){
			if(isConsonant(text[i])){//Wenn KONSONANT
				if(text[i]=='l'){//Wenn L
					text[i] = 'r';
				}
				if(text[i] == 'z'){//TSU-Fall
					if(i < text.length-1 && isVowel(text[i+1])){
						work = work+"ts"+text[i+1];
						i++;
						continue;
					}
						work = work+"tsu";
						continue;	
				}
				else if(i < text.length-1 && text[i] == text[i+1]){//DOPPELKONSONANT
					work = work+"#smallTSU";
					continue;
				}
				else if(i < text.length-3 && text[i] == 't' && text[i+1]=='s'){//CHI-Fall
					if(text[i+1]=='s' && text[i+2] == 'c' && text[i+3] == 'h'){
						work = work + "ch";
						i = i+3;
						if(i < text.length-1 && isConsonant(text[i+1]) && text[i] != 't' && text[i] != 'x'){//Sollte auf ch kein Vokal, sondern ein Konsonant, folgen
							work = work + "u";
						}
						continue;
					}
				}
				else if(text[i] == 'j'){//JI-Fall
					if(i < text.length-1 && isVowel(text[i+1])){
						work = work+ "j" + text[i+1];
						i++;
						continue;
					}
					work = work +"ji";
					continue;
				}
				else if(text[i]=='y'){//YA-,YU- und YO-Fall
					if(i < text.length-1 && text[i+1]=='y' || i < text.length-1 && text[i+1]=='u' || i < text.length-1 && text[i+1]=='o'){
						work = work+"y"+text[i+1];
						i++;
						continue;
					}
					else if(i < text.length-1 && isVowel(text[i+1])){
						work = work+text[i+1];
						i++;
						continue;
					}
					work = work +"yu";
					continue;
				}
				else if(i < text.length-1 && text[i+1]=='y'){//RYO-,usw.,HYO-Fall
					if(i < text.length-2 && isVowel(text[i+2])){
						work = work+text[i]+"y"+text[i+2];
						i = i+2;
						continue;
					}
					work = work+text[i]+"yu";
					i++;
					continue;
				}
				else if(i < text.length-1 && text[i]=='s'){//SHI-Fall
					if(text[i+1]=='h'){
						if(i < text.length-2 && isVowel(text[i+2])){
							work = work + "sh"+text[i+2];
							i = i+2;
							continue;
						}
						work = work + "shu";
						i++;
						continue;
					}
					else{
						if(isVowel(text[i+1])){
							work = work+"s"+text[i+1];
							i++;
							continue;
						}
						else{
							work = work+"su";
							continue;
						}
					}
				}
				else if(text[i] == 't'){//TO statt TU!
					if (i < text.length-1 && isVowel(text[i+1])){
						work = work+text[i]+text[i+1];
						i++;
						continue;
					}
					else if(i < text.length-1 && text[i+1] != 't'){
						work = work + "to";
						continue;
					}
				}
				else if(text[i] == 'd'){//DO statt DU!
					if (i < text.length-1 && isVowel(text[i+1])){
						work = work+text[i]+text[i+1];
						i++;
						continue;
					}
					else{
						work = work + "do";
						continue;
					}
				}
				else if(text[i] == 'n'){//N alleine geht!
					work = work+"n";
					continue;
				}
				//ACHTUNG NORMALFALL!!!!!
				else if(i < text.length-1 && isVowel(text[i+1])){//NORMAL-Fall
					work = work+text[i]+text[i+1];
					i++;
					continue;
				}//ENDE NORMALFALL
				work = work+text[i]+"u";//Kein CONTINUE mehr noetig
			}
			else{//Wenn VOKAL oder KEIN BUCHSTABE
				if(i > 0 && isVowel(text[i]) && text[i] == text[i-1]){//Doppel Vokal
					System.out.println("DOUBLE VOWEL");
					work = work +"-";
					continue;
				}
				work = work+text[i];
				continue;
			}
		}
		System.out.println("RomajiPreparer: Wort wurde erfolgreich an die japanische Sprache angepasst: '"+work+"'.");
		return work;
	}
	
	private static boolean isVowel(char probe){
		if(probe=='a' || probe=='i' || probe=='u' || probe=='e' || probe=='o'){
			return true;
		}
		else{
			return false;
		}
	}
	
	private static boolean isConsonant(char probe){
		String character = String.valueOf(probe);
		String consonants = "bcdfghjklmnpqrstvwxyz";
		if(consonants.contains(character)){
			return true;
		}
		else{
			return false;
		}
	}
	private static String preWork(String sas){
		if(sas.contains("eu")){
			sas = sas.replace("eu", "oi");
		}
		if(sas.contains("ei")){
			sas = sas.replace("ei", "ai");
		}
		if(sas.contains("sch")){
			sas = sas.replace("sch", "sh");
		}
		if(sas.contains("cher")){
			sas = sas.replace("cher", "haa");
		}
		if(sas.contains("aer") || sas.contains("ier") || sas.contains("uer")  || sas.contains("eer")  || sas.contains("oer") ){
			sas = sas.replace("er", "ya");
		}
		if(sas.contains("er")){
			sas = sas.replace("er", "ea");
		}
		if(sas.contains("h")){
			sas = sas.replace("ch", "h");
		}
		return sas;
	}
	public static String testConvert(String text){
		//Kleines TSU
		if(text.contains("#smallTSU")){
			text = text.replaceAll("#smallTSU", "ッ");
		}
		//-
		if(text.contains("-")){
			text = text.replace("-", "ー");
		}
		//xi
		if(text.contains("xi")){
			text = text.replace("xi", "クシ");
		}
		//X
		if(text.contains("x")){
			text = text.replace("x", "クシ");
		}
		//K
		if(text.contains("ka")){
			text = text.replace("ka", "カ");
		}
		if(text.contains("ki")){
			text = text.replace("ki", "キ");
		}
		if(text.contains("ku")){
			text = text.replace("ku", "ク");
		}
		if(text.contains("ke")){
			text = text.replace("ke", "ケ");
		}
		if(text.contains("ko")){
			text = text.replace("ko", "コ");
		}
		//G
		if(text.contains("ga")){
			text = text.replace("ga", "ガ");
		}
		if(text.contains("gi")){
			text = text.replace("gi", "ギ");
		}
		if(text.contains("gu")){
			text = text.replace("gu", "グ");
		}
		if(text.contains("ge")){
			text = text.replace("ge", "ゲ");
		}
		if(text.contains("go")){
			text = text.replace("go", "ゴ");
		}
		//S
		if(text.contains("sa")){
			text = text.replace("sa", "サ");
		}
		if(text.contains("shi")){
			text = text.replace("shi", "シ");
		}
		if(text.contains("su")){
			text = text.replace("su", "ス");
		}
		if(text.contains("se")){
			text = text.replace("se", "セ");
		}
		if(text.contains("so")){
			text = text.replace("so", "ソ");
		}
		//Z
		if(text.contains("za")){
			text = text.replace("za", "ザ");
		}
		if(text.contains("ji")){
			text = text.replace("ji", "ジ");
		}
		if(text.contains("zu")){
			text = text.replace("zu", "ズ");
		}
		if(text.contains("ze")){
			text = text.replace("ze", "ゼ");
		}
		if(text.contains("zo")){
			text = text.replace("zo", "ゾ");
		}
		//T
		if(text.contains("ta")){
			text = text.replace("ta", "タ");
		}
		if(text.contains("chi")){
			text = text.replace("chi", "チ");
		}
		if(text.contains("tsu")){
			text = text.replace("tsu", "ツ");
		}
		if(text.contains("te")){
			text = text.replace("te", "テ");
		}
		if(text.contains("to")){
			text = text.replace("to", "ト");
		}
		//D
		if(text.contains("da")){
			text = text.replace("da", "ダ");
		}
		if(text.contains("de")){
			text = text.replace("de", "デ");
		}
		if(text.contains("do")){
			text = text.replace("do", "ド");
		}
		//N
		if(text.contains("na")){
			text = text.replace("na", "ナ");
		}
		if(text.contains("ni")){
			text = text.replace("ni", "ニ");
		}
		if(text.contains("nu")){
			text = text.replace("nu", "ヌ");
		}
		if(text.contains("ne")){
			text = text.replace("ne", "ネ");
		}
		if(text.contains("no")){
			text = text.replace("no", "ノ");
		}
		//B
		if(text.contains("ba")){
			text = text.replace("ba", "バ");
		}
		if(text.contains("bi")){
			text = text.replace("bi", "ビ");
		}
		if(text.contains("bu")){
			text = text.replace("bu", "ブ");
		}
		if(text.contains("be")){
			text = text.replace("be", "ベ");
		}
		if(text.contains("bo")){
			text = text.replace("bo", "ボ");
		}
		//P
		if(text.contains("pa")){
			text = text.replace("pa", "パ");
		}
		if(text.contains("pi")){
			text = text.replace("pi", "ピ");
		}
		if(text.contains("pu")){
			text = text.replace("pu", "プ");
		}
		if(text.contains("pe")){
			text = text.replace("pe", "ペ");
		}
		if(text.contains("po")){
			text = text.replace("po", "ポ");
		}
		//M
		if(text.contains("ma")){
			text = text.replace("ma", "マ");
		}
		if(text.contains("mi")){
			text = text.replace("mi", "ミ");
		}
		if(text.contains("mu")){
			text = text.replace("mu", "ム");
		}
		if(text.contains("me")){
			text = text.replace("me", "メ");
		}
		if(text.contains("mo")){
			text = text.replace("mo", "モ");
		}
		//R
		if(text.contains("ra")){
			text = text.replace("ra", "ラ");
		}
		if(text.contains("ri")){
			text = text.replace("ri", "リ");
		}
		if(text.contains("ru")){
			text = text.replace("ru", "ル");
		}
		if(text.contains("re")){
			text = text.replace("re", "レ");
		}
		if(text.contains("ro")){
			text = text.replace("ro", "ロ");
		}
		//Y
		if(text.contains("ya")){
			text = text.replace("ya", "ヤ");
		}
		if(text.contains("yu")){
			text = text.replace("yu", "ユ");
		}
		if(text.contains("yo")){
			text = text.replace("yo", "ヨ");
		}
		//WA mit Special-Compound-Katakana
		if(text.contains("wa")){
			text = text.replace("wa", "ワ");
		}
		if(text.contains("wi")){
			text = text.replace("wi", "ウィ");
		}
		if(text.contains("wu")){
			text = text.replace("wu", "ウゥ");
		}
		if(text.contains("we")){
			text = text.replace("we", "ウェ");
		}
		if(text.contains("wo")){
			text = text.replace("wo", "ウォ");
		}
		//TS
		if(text.contains("tsa")){
			text = text.replace("tsa", "ツァ");
		}
		if(text.contains("tsi")){
			text = text.replace("tsi", "ツィ");
		}
		if(text.contains("tse")){
			text = text.replace("tse", "ツェ");
		}
		if(text.contains("tso")){
			text = text.replace("tso", "ツォ");
		}
		//TI, TU, DI, DU
		if(text.contains("ti")){
			text = text.replace("ti", "ティ");
		}
		if(text.contains("tu")){
			text = text.replace("tu", "トゥ");
		}
		if(text.contains("di")){
			text = text.replace("di", "ディ");
		}
		if(text.contains("du")){
			text = text.replace("du", "ドゥ");
		}
		//F
		if(text.contains("fa")){
			text = text.replace("fa", "ファ");
		}
		if(text.contains("fi")){
			text = text.replace("fi", "フィ");
		}
		if(text.contains("fe")){
			text = text.replace("fe", "フェ");
		}
		if(text.contains("fo")){
			text = text.replace("fo", "フォ");
		}
		//CHE
		if(text.contains("che")){
			text = text.replace("che", "チェ");
		}
		//JE
		if(text.contains("je")){
			text = text.replace("je", "ジェ");
		}
		//SHE
		if(text.contains("she")){
			text = text.replace("she", "シェ");
		}
		//Standard Compound-Katakana
		if(text.contains("rya")){
			text = text.replace("rya", "リャ");
		}
		if(text.contains("ryu")){
			text = text.replace("ryu", "リュ");
		}
		if(text.contains("ryo")){
			text = text.replace("ryo", "リョ");
		}
		if(text.contains("mya")){
			text = text.replace("mya", "ミャ");
		}
		if(text.contains("myu")){
			text = text.replace("myu", "ミュ");
		}
		if(text.contains("myo")){
			text = text.replace("myo", "ミョ");
		}
		if(text.contains("pya")){
			text = text.replace("pya", "ピャ");
		}
		if(text.contains("pyu")){
			text = text.replace("pyu", "ピュ");
		}
		if(text.contains("pyo")){
			text = text.replace("pyo", "ピョ");
		}
		if(text.contains("bya")){
			text = text.replace("bya", "ビャ");
		}
		if(text.contains("byu")){
			text = text.replace("byu", "ビュ");
		}
		if(text.contains("byo")){
			text = text.replace("byo", "ビョ");
		}
		if(text.contains("hya")){
			text = text.replace("hya", "ヒャ");
		}
		if(text.contains("hyu")){
			text = text.replace("hyu", "ヒュ");
		}
		if(text.contains("hyo")){
			text = text.replace("hyo", "ヒョ");
		}
		if(text.contains("nya")){
			text = text.replace("nya", "ニャ");
		}
		if(text.contains("nyu")){
			text = text.replace("nyu", "ニュ");
		}
		if(text.contains("nyo")){
			text = text.replace("nyo", "ニョ");
		}
		if(text.contains("cha")){
			text = text.replace("cha", "チャ");
		}
		if(text.contains("chu")){
			text = text.replace("chu", "チュ");
		}
		if(text.contains("cho")){
			text = text.replace("cho", "チョ");
		}
		if(text.contains("ja")){
			text = text.replace("ja", "ジャ");
		}
		if(text.contains("ju")){
			text = text.replace("ju", "ジュ");
		}
		if(text.contains("jo")){
			text = text.replace("jo", "ジョ");
		}
		if(text.contains("sha")){
			text = text.replace("sha", "シャ");
		}
		if(text.contains("shu")){
			text = text.replace("shu", "シュ");
		}
		if(text.contains("sho")){
			text = text.replace("sho", "ショ");
		}
		if(text.contains("gya")){
			text = text.replace("gya", "ギャ");
		}
		if(text.contains("gyu")){
			text = text.replace("gyu", "ギュ");
		}
		if(text.contains("gyo")){
			text = text.replace("gyo", "ギョ");
		}
		if(text.contains("kya")){
			text = text.replace("kya", "キャ");
		}
		if(text.contains("kyu")){
			text = text.replace("kyu", "キュ");
		}
		if(text.contains("kyo")){
			text = text.replace("kyo", "キョ");
		}
		//H
		if(text.contains("ha")){
			text = text.replace("ha", "ハ");
		}
		if(text.contains("hi")){
			text = text.replace("hi", "ヒ");
		}
		if(text.contains("fu")){
			text = text.replace("fu", "フ");
		}
		if(text.contains("he")){
			text = text.replace("he", "ヘ");
		}
		if(text.contains("ho")){
			text = text.replace("ho", "ホ");
		}
		//Vokale
		if(text.contains("a")){
			text = text.replace("a", "ア");
		}
		if(text.contains("i")){
			text = text.replace("i", "イ");
		}
		if(text.contains("u")){
			text = text.replace("u", "ウ");
		}
		if(text.contains("e")){
			text = text.replace("e", "エ");
		}
		if(text.contains("o")){
			text = text.replace("o", "オ");
		}
		//N
		if(text.contains("n")){
			text = text.replace("n", "ン");
		}
		return text;
	}

}
