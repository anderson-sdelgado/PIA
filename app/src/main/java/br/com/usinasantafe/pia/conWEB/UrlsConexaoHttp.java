package br.com.usinasantafe.pia.conWEB;

public class UrlsConexaoHttp {

    public static String datahorahttp = "http://www.usinasantafe.com.br/pia/datahora.php";

    public static String urlPrincipal = "http://www.usinasantafe.com.br/piadev/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/piadev/";

    public static String localPSTVariavel = "br.com.usinasantafe.pia.tb.variaveis.";
    public static String localPSTEstatica = "br.com.usinasantafe.pia.tb.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pia.conWEB.UrlsConexaoHttp";

    public static String AmostraTO = urlPrincipal + "amostra.php";
    public static String CaracOrganTO = urlPrincipal + "caracorgan.php";
    public static String OrganTO = urlPrincipal + "organ.php";
    public static String ROrganCaracTO = urlPrincipal + "rorgancarac.php";
    public static String RCaracAmostraTO = urlPrincipal + "rcaracamostra.php";
    public static String AuditorTO = urlPrincipal + "auditor.php";
    public static String SecaoTO = urlPrincipal + "secao.php";
    public static String TalhaoTO = urlPrincipal + "talhao.php";

    public UrlsConexaoHttp() {
        // TODO Auto-generated constructor stub
    }

    public String getsApontaAnalise() {
        return urlPrincEnvio + "apontaanalise.php";
    }

    public String getsInsertAponta() {
        return urlPrincEnvio + "insapont.php";
    }

    public String getsInsertBolAberto() {
        return urlPrincEnvio + "insbolaberto.php";
    }

    public String getsInsertBolFechado() {
        return urlPrincEnvio + "insbolfechado.php";
    }

    public String getsEditAponta() {
        return urlPrincEnvio + "editapont.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincEnvio + "verequip.php";
        } else if (classe.equals("OS")) {
            retorno = urlPrincEnvio + "veros.php";
        } else if (classe.equals("Ativ")) {
            retorno = urlPrincEnvio + "atualosativ.php";
        } else if (classe.equals("Parada")) {
            retorno = urlPrincEnvio + "atualclassepar.php";
        }
        return retorno;
    }

}
