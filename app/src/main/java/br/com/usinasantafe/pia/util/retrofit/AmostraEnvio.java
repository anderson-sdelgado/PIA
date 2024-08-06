package br.com.usinasantafe.pia.util.retrofit;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import br.com.usinasantafe.pia.control.InfestacaoCTR;
import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import br.com.usinasantafe.pia.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pia.model.dao.LogErroDAO;
import br.com.usinasantafe.pia.util.EnvioDadosServ;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmostraEnvio {

    public AmostraEnvio() {
    }

    public void envioDadosAmostra(List<CabecAmostraBean> cabecAmostraBeanList, String activity) {

        try {

            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            AmostraDao amostraDao = ConnRetrofit.getInstance().conn().create(AmostraDao.class);
            Call<List<CabecAmostraBean>> call = amostraDao.envioDadosAmostra(cabecAmostraBeanList, "Bearer " + atualAplicDAO.token());
            call.enqueue(new Callback<>() {

                @Override
                public void onResponse(Call<List<CabecAmostraBean>> call, Response<List<CabecAmostraBean>> response) {
                    InfestacaoCTR infestacaoCTR = new InfestacaoCTR();
                    infestacaoCTR.updateAmostraEnviada(response.body(), activity);
                }

                @Override
                public void onFailure(Call<List<CabecAmostraBean>> call, Throwable t) {
                    LogErroDAO.getInstance().insertLogErro(t);
                    EnvioDadosServ.getInstance().respostaEnvio(false, activity);
                }

            });

        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
            EnvioDadosServ.getInstance().respostaEnvio(false, activity);
        }

    }
}
