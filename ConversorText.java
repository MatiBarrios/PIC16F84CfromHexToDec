package Traductor;
public class ConversorText{
	//metodo constructor
	public ConversorText() {}
	
	public String convertir(String valor) {
		//se convierte el valor a mayusculas para su correcto uso
		valor=valor.toUpperCase();
		//Coloco los valores de la operacion NOP aqui porque sino el codigo queda muy ilegible
		//primero comparamos el primer valor y lo dividimos en 3 partes 0, 1, 2 y 3
		//para tratarlos por separado, ocupando un switch
		if (valor.equals("0000") || valor.equals("0020")|| valor.equals("0040")) {return("NOP (no operation)");}
		else {
			switch(valor.charAt(0)) {
			case '0':
				//ahora lo dividimos en las 16 diferentes operaciones que inician con el valor 0
				switch(valor.charAt(1)) {
				case '0':
					switch(valor.charAt(2)) {
					case '6':
						if(valor.charAt(3)=='3') {return "SLEEP (duerme)";}
						else if(valor.charAt(3)=='4') {return "CLRWDT (limpiar TimeWhatchDog";}
						else {return "Error rango 3, valor "+valor.charAt(3)+" invalido";}
					case '0':
						if(valor.charAt(3)=='8') {return "RETURN (vuelve de una sub rutina)";}
						else if(valor.charAt(3)=='9') {return "RETFIE (vuelve de interrupcion)";}
						else {return "Error en rango 3, valor "+valor.charAt(3)+" invalido";}
					default:
						return this.De8aF("MOVWF", valor, false);
					}
				case '1':
					if(this.mayor7(valor)) {return this.De8aF("CLRF", valor, false);}
					else {return ("CLRW (limpia el valor en el acumulador)");}
				case '2':
					if(this.mayor7(valor)) {return this.De8aF("SUBWF", valor, true);}
					else {return ("SUBWF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case '3':
					if(this.mayor7(valor)) {return this.De8aF("DECF", valor, true);}
					else {return ("DECF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case '4':
					if(this.mayor7(valor)) {return this.De8aF("IORWF", valor, true);}
					else {return ("IORWF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case '5':
					if(this.mayor7(valor)) {return this.De8aF("ANDWF", valor, true);}
					else {return ("ANDWF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case '6':
					if(this.mayor7(valor)) {return this.De8aF("XORWF", valor, true);}
					else {return ("XORWF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case '7':
					if(this.mayor7(valor)) {return this.De8aF("ADDWF", valor, true);}
					else {return ("ADDWF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case '8':
					if(this.mayor7(valor)) {return this.De8aF("MOVF", valor, true);}
					else {return ("MOVWF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case '9':
					if(this.mayor7(valor)) {return this.De8aF("COMF", valor, true);}
					else {return ("COMF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case 'A':
					if(this.mayor7(valor)) {return this.De8aF("INCF", valor, true);}
					else {return ("INCF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case 'B':
					if(this.mayor7(valor)) {return this.De8aF("DECFSZ", valor, true);}
					else {return ("DECFSZ 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case 'C':
					if(this.mayor7(valor)) {return this.De8aF("RRF", valor, true);}
					else {return ("RRF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case 'D':
					if(this.mayor7(valor)) {return this.De8aF("RLF", valor, true);}
					else {return ("RLF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case 'E':
					if(this.mayor7(valor)) {return this.De8aF("SWAPF", valor, true);}
					else {return ("SWAPF 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				case 'F':
					if(this.mayor7(valor)) {return this.De8aF("INCFSZ", valor, true);}
					else {return ("INCFSZ 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
				default:
					return ("Error rango 1, valor "+valor.charAt(1)+" invalido");
				}
			case '1':
				if      (valor.charAt(1)=='0' || valor.charAt(1)=='1' || valor.charAt(1)=='2' || valor.charAt(1)=='3') {return this.adapBinario("BCF", valor);}
				else if (valor.charAt(1)=='4' || valor.charAt(1)=='5' || valor.charAt(1)=='6' || valor.charAt(1)=='7') {return this.adapBinario("BSF", valor);}
				else if (valor.charAt(1)=='8' || valor.charAt(1)=='9' || valor.charAt(1)=='A' || valor.charAt(1)=='B') {return this.adapBinario("BTFSC", valor);}
				else if (valor.charAt(1)=='C' || valor.charAt(1)=='D' || valor.charAt(1)=='E' || valor.charAt(1)=='F') {return this.adapBinario("BTFSS", valor);}
				else {return ("Error en rango 1, valor"+valor.charAt(1)+" invalido.");}
			case '2':
				if (this.mayor7(valor,1)) {
					switch(valor.charAt(1)){
                    case '8':
                        return("GOTO 0x0"+valor.charAt(2)+valor.charAt(3));
                    case '9':
                        return("GOTO 0x1"+valor.charAt(2)+valor.charAt(3));
                    case 'A':
                        return("GOTO 0x2"+valor.charAt(2)+valor.charAt(3));
                    case 'B':
                        return("GOTO 0x3"+valor.charAt(2)+valor.charAt(3));
                    case 'C':
                        return("GOTO 0x4"+valor.charAt(2)+valor.charAt(3));
                    case 'D':
                        return("GOTO 0x5"+valor.charAt(2)+valor.charAt(3));
                    case 'E':
                        return("GOTO 0x6"+valor.charAt(2)+valor.charAt(3));
                    case 'F':
                        return("GOTO 0x7"+valor.charAt(2)+valor.charAt(3));
                    default:
                        return("Error en rango 1, valor "+valor.charAt(1)+" invalido.");
					}
				}else{
                    return("CALL 0x"+valor.charAt(1)+valor.charAt(2)+valor.charAt(3));
                }
			case '3':
				//por alguna razon no usa B lo cual quiere decir que sobra 1 poderosa instruccion en el set de instrucciones.
				if     (valor.charAt(1)=='0'||valor.charAt(1)=='1'||valor.charAt(1)=='2'||valor.charAt(1)=='3'){return("MOVLW 0x"+valor.charAt(2)+valor.charAt(3));}
				else if(valor.charAt(1)=='4'||valor.charAt(1)=='5'||valor.charAt(1)=='6'||valor.charAt(1)=='7'){return("RETLW 0x"+valor.charAt(2)+valor.charAt(3));}
				else if(valor.charAt(1)=='8'){return("IORLW 0x"+valor.charAt(2)+valor.charAt(3));}
				else if(valor.charAt(1)=='9'){return("ANDLW 0x"+valor.charAt(2)+valor.charAt(3));}
				else if(valor.charAt(1)=='A'){return("XORLW 0x"+valor.charAt(2)+valor.charAt(3));}
				else if(valor.charAt(1)=='C'||valor.charAt(1)=='D'){return("SUBLW 0x"+valor.charAt(2)+valor.charAt(3));}
				else if(valor.charAt(1)=='F'||valor.charAt(1)=='E'){return("ADDLW 0x"+valor.charAt(2)+valor.charAt(3));}
				break;
			default:
				return("Error rango 0, valor "+valor.charAt(0)+" invalido");
			}//fin switch char 0
		}//fin else
		return "Error, y nose de que la verdad, java insiste en que ponga esto o el codigo no funciona";
	}
	
	//un metodo que devuelve un booleano verdadero si el valor en el rango 2 es mayor a 7
	public boolean mayor7(String valor) {
		if (valor.charAt(2)=='0' || valor.charAt(2)=='1' || valor.charAt(2)=='2' || valor.charAt(2)=='3' || valor.charAt(2)=='4' || valor.charAt(2)=='5' || valor.charAt(2)=='6' || valor.charAt(2)=='7') {
			return false;
		}else {return true;}
	}
	
	//un metodo que devuelve un booleano verdadero si el valor en el rango x es mayor a 7
	public boolean mayor7(String valor, int rango) {
		if (valor.charAt(rango)=='0' || valor.charAt(rango)=='1' || valor.charAt(rango)=='2' || valor.charAt(rango)=='3' || valor.charAt(rango)=='4' || valor.charAt(rango)=='5' || valor.charAt(rango)=='6' || valor.charAt(rango)=='7') {
			return false;
		}else {return true;}
	}
	
	public String adapBinario(String inst, String valor) {
		if (this.mayor7(valor)) {
			switch(valor.charAt(2)) {
			case '8':
				switch(valor.charAt(1)) {
				case '0':
					return(inst+" 0x0"+valor.charAt(3)+", 1");
				case '1':
					return(inst+" 0x0"+valor.charAt(3)+", 3");
				case '2':
					return(inst+" 0x0"+valor.charAt(3)+", 5");
				default:
					return(inst+" 0x0"+valor.charAt(3)+", 7");
				}
			case '9':
				switch(valor.charAt(1)) {
				case '0':
					return(inst+" 0x1"+valor.charAt(3)+", 1");
				case '1':
					return(inst+" 0x1"+valor.charAt(3)+", 3");
				case '2':
					return(inst+" 0x1"+valor.charAt(3)+", 5");
				default:
					return(inst+" 0x1"+valor.charAt(3)+", 7");
				}
			case 'A':
				switch(valor.charAt(1)) {
				case '0':
					return(inst+" 0x2"+valor.charAt(3)+", 1");
				case '1':
					return(inst+" 0x2"+valor.charAt(3)+", 3");
				case '2':
					return(inst+" 0x2"+valor.charAt(3)+", 5");
				default:
					return(inst+" 0x2"+valor.charAt(3)+", 7");
				}
			case 'B':
				switch(valor.charAt(1)) {
				case '0':
					return(inst+" 0x3"+valor.charAt(3)+", 1");
				case '1':
					return(inst+" 0x3"+valor.charAt(3)+", 3");
				case '2':
					return(inst+" 0x3"+valor.charAt(3)+", 5");
				default:
					return(inst+" 0x3"+valor.charAt(3)+", 7");
				}
			case 'C':
				switch(valor.charAt(1)) {
				case '0':
					return(inst+" 0x4"+valor.charAt(3)+", 1");
				case '1':
					return(inst+" 0x4"+valor.charAt(3)+", 3");
				case '2':
					return(inst+" 0x4"+valor.charAt(3)+", 5");
				default:
					return(inst+" 0x4"+valor.charAt(3)+", 7");
				}
			case 'D':
				switch(valor.charAt(1)) {
				case '0':
					return(inst+" 0x5"+valor.charAt(3)+", 1");
				case '1':
					return(inst+" 0x5"+valor.charAt(3)+", 3");
				case '2':
					return(inst+" 0x5"+valor.charAt(3)+", 5");
				default:
					return(inst+" 0x5"+valor.charAt(3)+", 7");
				}
			case 'E':
				switch(valor.charAt(1)) {
				case '0':
					return(inst+" 0x6"+valor.charAt(3)+", 1");
				case '1':
					return(inst+" 0x6"+valor.charAt(3)+", 3");
				case '2':
					return(inst+" 0x6"+valor.charAt(3)+", 5");
				default:
					return(inst+" 0x6"+valor.charAt(3)+", 7");
				}
			case 'F':
				switch(valor.charAt(1)) {
				case '0':
					return(inst+" 0x7"+valor.charAt(3)+", 1");
				case '1':
					return(inst+" 0x7"+valor.charAt(3)+", 3");
				case '2':
					return(inst+" 0x7"+valor.charAt(3)+", 5");
				default:
					return (inst+" 0x7"+valor.charAt(3)+", 7");
				}
			default:
				return ("Error en rango 2, valor "+valor.charAt(2)+" invalido.");
			}
				
		}//fin if de mayor7
		else {
			if     (valor.charAt(1)=='0' || valor.charAt(1)=='4' || valor.charAt(1)=='8' || valor.charAt(1)=='C'){return(inst+" 0x"+valor.charAt(2)+valor.charAt(3)+", 0");}
			else if(valor.charAt(1)=='1' || valor.charAt(1)=='5' || valor.charAt(1)=='9' || valor.charAt(1)=='D'){return(inst+" 0x"+valor.charAt(2)+valor.charAt(3)+", 2");}
			else if(valor.charAt(1)=='2' || valor.charAt(1)=='6' || valor.charAt(1)=='A' || valor.charAt(1)=='E'){return(inst+" 0x"+valor.charAt(2)+valor.charAt(3)+", 4");}
			else if(valor.charAt(1)=='3' || valor.charAt(1)=='7' || valor.charAt(1)=='B' || valor.charAt(1)=='F'){return(inst+" 0x"+valor.charAt(2)+valor.charAt(3)+", 6");}
			else {return ("Error en rango 1, valor "+valor.charAt(1)+" invalido.");}
		}
	}
	
	//recibe los valores en caso de encontrarse entre 8 y F, los trabaja y hace una salida
	//recibe la instruccion, el string hexa y un desvio para representar si va a W o a F
	public String De8aF(String inst, String valor, boolean desvio ) {
		switch(valor.charAt(2)) {
		case '8':
			if (desvio) {return inst+" 0x0"+valor.charAt(3)+", 1";}
			else {return inst+" 0x0"+valor.charAt(3);}
		case '9':
			if (desvio) {return inst+" 0x1"+valor.charAt(3)+", 1";}
			else {return inst+" 0x1"+valor.charAt(3);}
		case 'A':
			if (desvio) {return inst+" 0x2"+valor.charAt(3)+", 1";}
			else {return inst+" 0x2"+valor.charAt(3);}
		case 'B':
			if (desvio) {return inst+" 0x3"+valor.charAt(3)+", 1";}
			else {return inst+" 0x3"+valor.charAt(3);}
		case 'C':
			if (desvio) {return inst+" 0x4"+valor.charAt(3)+", 1";}
			else {return inst+" 0x4"+valor.charAt(3);}
		case 'D':
			if (desvio) {return inst+" 0x5"+valor.charAt(3)+", 1";}
			else {return inst+" 0x5"+valor.charAt(3);}
		case 'E':
			if (desvio) {return inst+" 0x6"+valor.charAt(3)+", 1";}
			else {return inst+" 0x6"+valor.charAt(3);}
		case 'F':
			if (desvio) {return inst+" 0x7"+valor.charAt(3)+", 1";}
			else {return inst+" 0x7"+valor.charAt(3);}
		default:
			return "Error en rango 2, valor "+valor.charAt(2)+" invalido";
		}
	}
}
