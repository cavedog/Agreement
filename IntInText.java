package AgreementMaker;

	import java.lang.reflect.Field;

	public class IntInText {
	public final static String x1="����";
	public final static String xx1="����";

	public final static String x2="���";
	public final static String xx2="��i";


	public final static String x3="���";
	public final static String x4="������";
	public final static String x5="�'���";
	public final static String x6="�i���";
	public final static String x7="�i�";
	public final static String x8="�i�i�";
	public final static String x9="���'���";
	public final static String x10="������";

	public final static String x11="����������";
	public final static String x12="����������";
	public final static String x13="����������";
	public final static String x14="������������";
	public final static String x15="�'���������";
	public final static String x16="�i���������";
	public final static String x17="�i��������";
	public final static String x18="�i�i��������";
	public final static String x19="���'���������";

	public final static String x20="��������";
	public final static String x30="��������";
	public final static String x40="�����";
	public final static String x50="�'�������";
	public final static String x60="�i�������";
	public final static String x70="�i������";
	public final static String x80="�i�i������";
	public final static String x90="���'������";

	public final static String x100="���";
	public final static String x200="��i��i";
	public final static String x300="������";
	public final static String x400="���������";
	public final static String x500="�'�����";
	public final static String x600="�i�����";
	public final static String x700="�i����";
	public final static String x800="�i�i����";
	public final static String x900="���'�����";

	public final static String x1000="������";
	public final static String xx1000="�����i";
	public final static String x�x1000="�����";



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
	res=" "+fild.get(clas).toString()+" "+x�x1000+res;
	}
	}	

	if(x>9999 && no.charAt(no.length()-5)=='1'){
	fild = IntInText.class.getField("x1"+no.charAt(no.length()-4));
	res=" "+fild.get(clas).toString()+" "+x�x1000+res;
	}

	if(x>19999){

	fild = IntInText.class.getField("x"+no.charAt(no.length()-5)+"0");
	if(no.charAt(no.length()-4)=='0')
	res=" "+fild.get(clas).toString()+" "+x�x1000+res;
	else
	res=" "+fild.get(clas).toString()+res;	
	}	

	}catch(Exception e){
	e.printStackTrace();
	}

	return res;
	}

	}
