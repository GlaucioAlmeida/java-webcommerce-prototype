package classesbd;

public class CarrinhoUtils {
	public static int[] getFromCookieValue(String aux){
		if(aux=="") return null;
		String[] strArray=aux.split(",");
		int[] carrinho=new int[strArray.length];
		for(int i=0;i<carrinho.length;i++){
			carrinho[i]=Integer.parseInt(strArray[i]);//cod dos produtos
		}
		return carrinho;
	}

	public static String add(String value, String codProduto) {
		if(value.contains(codProduto))
			return value;
		else
			return value+codProduto+",";
	}
}
