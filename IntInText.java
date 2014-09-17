package AgreementMaker;

	import java.lang.reflect.Field;

	public class IntInText {
	public final static String x1="один";
	public final static String xx1="одна";

	public final static String x2="два";
	public final static String xx2="двi";


	public final static String x3="три";
	public final static String x4="чотири";
	public final static String x5="п'€ть";
	public final static String x6="шiсть";
	public final static String x7="сiм";
	public final static String x8="вiсiм";
	public final static String x9="дев'€ть";
	public final static String x10="дес€ть";

	public final static String x11="одинадц€ть";
	public final static String x12="дванадц€ть";
	public final static String x13="тринадц€ть";
	public final static String x14="чотирнадц€ть";
	public final static String x15="п'€тнадц€ть";
	public final static String x16="шiстнадц€ть";
	public final static String x17="сiмнадц€ть";
	public final static String x18="вiсiмнадц€ть";
	public final static String x19="дев'€тнадц€ть";

	public final static String x20="двадц€ть";
	public final static String x30="тридц€ть";
	public final static String x40="сорок";
	public final static String x50="п'€тдес€т";
	public final static String x60="шiстдес€т";
	public final static String x70="сiмдес€т";
	public final static String x80="вiсiмдес€т";
	public final static String x90="дев'€носто";

	public final static String x100="сто";
	public final static String x200="двiстi";
	public final static String x300="триста";
	public final static String x400="чотириста";
	public final static String x500="п'€тсот";
	public final static String x600="шiстсот";
	public final static String x700="сiмсот";
	public final static String x800="вiсiмсот";
	public final static String x900="дев'€тсот";

	public final static String x1000="тис€ча";
	public final static String xx1000="тис€чi";
	public final static String xхx1000="тис€ч";



	public static String convert(int x){
	String no=String.valueOf(x);
	String res="";
	Field fild;
	try{
	Class<IntInText> clas = IntInText.class;

	if(no.charAt(no.length()-1)!='0'){
	fild = IntInText.class.getField("x"+no.charAt(no.length()-1));
	res=" "+fild.get(clas).toString();
	}

	if(no.length()-2>-1 && no.charAt(no.length()-2)=='1'){
	fild = IntInText.class.getField("x1"+no.charAt(no.length()-1));
	res=" "+fild.get(clas).toString();
	}
	//if(x>19 && no.charAt(no.length()-1)!='0'){
	if(x>19 && no.charAt(no.length()-2)!='0'){
	fild = IntInText.class.getField("x"+no.charAt(no.length()-2)+"0");
	res=" "+fild.get(clas).toString()+res;
	}

	if(x>99 && no.charAt(no.length()-3)!='0'){
	fild = IntInText.class.getField("x"+no.charAt(no.length()-3)+"00");
	res=" "+fild.get(clas).toString()+res;
	}


	if(x>999 && no.length()-5==-1){	
	String isRes=no.charAt(no.length()-4)+"";

	if(isRes.equals("1")){
	fild = IntInText.class.getField("xx"+no.charAt(no.length()-4));
	res=" "+fild.get(clas).toString()+" "+x1000+res;
	}
	if(isRes.equals("2")){
	fild = IntInText.class.getField("xx"+no.charAt(no.length()-4));
	res=" "+fild.get(clas).toString()+" "+xx1000+res;
	}	
	if(isRes.equals("3")|| isRes.equals("4")){
	fild = IntInText.class.getField("x"+no.charAt(no.length()-4));
	res=" "+fild.get(clas).toString()+" "+xx1000+res;
	}
	if(no.charAt(no.length()-4)!='0' && !isRes.equals("1")&&!isRes.equals("2") && !isRes.equals("3") && !isRes.equals("4")){
	fild = IntInText.class.getField("x"+no.charAt(no.length()-4));
	res=" "+fild.get(clas).toString()+" "+xхx1000+res;
	}
	}	

	if(x>9999 && no.charAt(no.length()-5)=='1'){
	fild = IntInText.class.getField("x1"+no.charAt(no.length()-4));
	res=" "+fild.get(clas).toString()+" "+xхx1000+res;
	}

	if(x>19999){

	fild = IntInText.class.getField("x"+no.charAt(no.length()-5)+"0");
	if(no.charAt(no.length()-4)=='0')
	res=" "+fild.get(clas).toString()+" "+xхx1000+res;
	else
	res=" "+fild.get(clas).toString()+res;	
	}	

	}catch(Exception e){
	e.printStackTrace();
	}

	return res;
	}

	}
