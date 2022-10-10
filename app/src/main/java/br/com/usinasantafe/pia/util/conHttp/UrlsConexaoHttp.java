package br.com.usinasantafe.pia.util.conHttp;

import br.com.usinasantafe.pia.PIAContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + PIAContext.versaoWS.replace(".", "_");

    public static String url = "https://www.usinasantafe.com.br/piadev/view/";
//    public static String url = "https://www.usinasantafe.com.br/piaqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/piaprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pia.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pia.util.conHttp.UrlsConexaoHttp";

    public static String AmostraBean = url + "amostra.php";
    public static String AuditorBean = url + "auditor.php";
    public static String CaracOrganBean = url + "caracorgan.php";
    public static String OrganBean = url + "organ.php";
    public static String OSBean = url + "os.php";
    public static String RCaracAmostraBean = url + "rcaracamostra.php";
    public static String ROrganCaracBean = url + "rorgancarac.php";
    public static String SecaoBean = url + "secao.php";
    public static String TalhaoBean = url + "talhao.php";

    public UrlsConexaoHttp() {
    }

    public String getsInsertBoletim() {
        return url + "inserirboletim.php";
    }

    public String urlVerifica(String classe) {
        return "";
    }

}
