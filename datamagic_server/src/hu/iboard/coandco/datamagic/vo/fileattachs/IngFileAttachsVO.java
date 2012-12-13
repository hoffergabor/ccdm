package hu.iboard.coandco.datamagic.vo.fileattachs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INGFILEATTACHS")
public class IngFileAttachsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8160523849179566419L;

	private Integer id;
	private String fileName;
	private Date letrehozva;
	private Date moddatum;
	private String name;
	private Integer hibabejId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "FILENAME", unique = true, nullable = false, length = 255)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "LETREHOZVA")
	public Date getLetrehozva() {
		return letrehozva;
	}

	public void setLetrehozva(Date letrehozva) {
		this.letrehozva = letrehozva;
	}

	@Column(name = "MODDATUM")
	public Date getModdatum() {
		return moddatum;
	}

	public void setModdatum(Date moddatum) {
		this.moddatum = moddatum;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "HIBABEJ_ID")
	public Integer getHibabejId() {
		return hibabejId;
	}

	public void setHibabejId(Integer hibabejId) {
		this.hibabejId = hibabejId;
	}
	
}
