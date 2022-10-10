/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.pia.model.bean.estaticas;

/**
 *
 * @author anderson
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pia.model.pst.Entidade;

@DatabaseTable(tableName="tbosest")
public class OSBean extends Entidade {

	private static final long serialVersionUID = 1L;

	@DatabaseField(id=true)
	private Long idOS;
	@DatabaseField
    private Long nroOS;
	@DatabaseField
	private Long idLibOS;
	@DatabaseField
	private Long idProprAgr;

    public OSBean() {
    }

	public Long getIdOS() {
		return idOS;
	}

	public void setIdOS(Long idOS) {
		this.idOS = idOS;
	}

	public Long getNroOS() {
		return nroOS;
	}

	public void setNroOS(Long nroOS) {
		this.nroOS = nroOS;
	}

	public Long getIdLibOS() {
		return idLibOS;
	}

	public void setIdLibOS(Long idLibOS) {
		this.idLibOS = idLibOS;
	}

	public Long getIdProprAgr() {
		return idProprAgr;
	}

	public void setIdProprAgr(Long idProprAgr) {
		this.idProprAgr = idProprAgr;
	}

}
