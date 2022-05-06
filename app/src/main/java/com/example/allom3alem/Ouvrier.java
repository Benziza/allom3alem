package com.example.allom3alem;

public class Ouvrier {
    private String CINOUVRIER;
    private int IDMETIER;
    private String NOMVILLE;
    private String NOM;
    private String IDTRAVAIL;
    private String MOBILE;
    private String ADRESSE;
    private String DESCRIPTION;
    private double RATING;
    private byte[] PHOTO;
    private String DISPONIBILITE;
    private int OCCURENCE;
    private byte[] PHOTOTRAVAIL;
    private String PASSWORD;


    public Ouvrier()
    {

    }
    public Ouvrier(String CINOUVRIER, String NOM, double RATING, int OCCURENCE, String DISPONIBILITE, int IDMETIER, String NOMVILLE, byte[] PHOTO, String DESCRIPTION, String ADRESSE, String MOBILE, byte[] PHOTOTRAVAIL, String PASSWORD)
    {
        this.NOM = NOM;
        this.RATING = RATING;
        this.OCCURENCE = OCCURENCE;
        this.DISPONIBILITE = DISPONIBILITE;
        this.IDMETIER = IDMETIER;
        this.NOMVILLE = NOMVILLE;
        this.PHOTO = PHOTO;
        this.DESCRIPTION = DESCRIPTION;
        this.ADRESSE = ADRESSE;
        this.MOBILE = MOBILE;
        this.CINOUVRIER = CINOUVRIER;
        this.PHOTOTRAVAIL = PHOTOTRAVAIL;
        this.PASSWORD = PASSWORD;
    }

    public Ouvrier(String CINOUVRIER,String NOM,double RATING,int OCCURENCE,String DISPONIBILITE,int IDMETIER,String NOMVILLE,byte[] PHOTO,String DESCRIPTION,String ADRESSE,String MOBILE,byte[] PHOTOTRAVAIL)
    {
        this.NOM = NOM;
        this.RATING = RATING;
        this.OCCURENCE = OCCURENCE;
        this.DISPONIBILITE = DISPONIBILITE;
        this.IDMETIER = IDMETIER;
        this.NOMVILLE = NOMVILLE;
        this.PHOTO = PHOTO;
        this.DESCRIPTION = DESCRIPTION;
        this.ADRESSE = ADRESSE;
        this.MOBILE = MOBILE;
        this.CINOUVRIER = CINOUVRIER;
        this.PHOTOTRAVAIL = PHOTOTRAVAIL;
    }

    public Ouvrier(String CINOUVRIER,String NOM,double RATING,int OCCURENCE,String DISPONIBILITE,int IDMETIER,String NOMVILLE,byte[] PHOTO,String DESCRIPTION,String ADRESSE,String MOBILE)
    {
        this.NOM = NOM;
        this.RATING = RATING;
        this.OCCURENCE = OCCURENCE;
        this.DISPONIBILITE = DISPONIBILITE;
        this.IDMETIER = IDMETIER;
        this.NOMVILLE = NOMVILLE;
        this.PHOTO = PHOTO;
        this.DESCRIPTION = DESCRIPTION;
        this.ADRESSE = ADRESSE;
        this.MOBILE = MOBILE;
        this.CINOUVRIER = CINOUVRIER;
    }

    public Ouvrier(String NOM,double RATING,int OCCURENCE,String DISPONIBILITE,int IDMETIER,String NOMVILLE,byte[] PHOTO,String DESCRIPTION,String ADRESSE,String MOBILE)
    {
        this.NOM = NOM;
        this.RATING = RATING;
        this.OCCURENCE = OCCURENCE;
        this.DISPONIBILITE = DISPONIBILITE;
        this.IDMETIER = IDMETIER;
        this.NOMVILLE = NOMVILLE;
        this.PHOTO = PHOTO;
        this.DESCRIPTION = DESCRIPTION;
        this.ADRESSE = ADRESSE;
        this.MOBILE = MOBILE;
    }

    public Ouvrier(String NOM,double RATING,int OCCURENCE,String DISPONIBILITE,int IDMETIER,String NOMVILLE,byte[] PHOTO,String DESCRIPTION,String ADRESSE)
    {
        this.NOM = NOM;
        this.RATING = RATING;
        this.OCCURENCE = OCCURENCE;
        this.DISPONIBILITE = DISPONIBILITE;
        this.IDMETIER = IDMETIER;
        this.NOMVILLE = NOMVILLE;
        this.PHOTO = PHOTO;
        this.DESCRIPTION = DESCRIPTION;
        this.ADRESSE = ADRESSE;
    }
    public Ouvrier(String NOM,double RATING,int OCCURENCE,String DISPONIBILITE,int IDMETIER,String NOMVILLE,byte[] PHOTO)
    {
        this.NOM = NOM;
        this.RATING = RATING;
        this.OCCURENCE = OCCURENCE;
        this.DISPONIBILITE = DISPONIBILITE;
        this.IDMETIER = IDMETIER;
        this.NOMVILLE = NOMVILLE;
        this.PHOTO = PHOTO;
        this.DESCRIPTION = DESCRIPTION;
        this.ADRESSE = ADRESSE;
    }

    public Ouvrier(String NOM,double RATING,int OCCURENCE,String DISPONIBILITE,byte[] PHOTO)
    {
        this.NOM = NOM;
        this.RATING = RATING;
        this.OCCURENCE = OCCURENCE;
        this.DISPONIBILITE = DISPONIBILITE;
        this.PHOTO = PHOTO;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public double getRATING() {
        return RATING;
    }

    public void setRATING(double RATING) {
        this.RATING = RATING;
    }

    public Ouvrier(String NOM, double RATING, String DISPONIBILITE, int OCCURENCE) {
        this.NOM = NOM;
        this.RATING = RATING;
        this.DISPONIBILITE = DISPONIBILITE;
        this.OCCURENCE = OCCURENCE;
    }

    public Ouvrier(String CINOUVRIER, int IDMETIER, String NOMVILLE, String NOM, String IDTRAVAIL, String MOBILE, String ADRESSE, String DESCRIPTION, double RATING, byte[] PHOTO, String DISPONIBILITE, int OCCURENCE, byte[] PHOTOTRAVAIL) {
        this.CINOUVRIER = CINOUVRIER;
        this.IDMETIER = IDMETIER;
        this.NOMVILLE = NOMVILLE;
        this.NOM = NOM;
        this.IDTRAVAIL = IDTRAVAIL;
        this.MOBILE = MOBILE;
        this.ADRESSE = ADRESSE;
        this.DESCRIPTION = DESCRIPTION;
        this.RATING = RATING;
        this.PHOTO = PHOTO;
        this.DISPONIBILITE = DISPONIBILITE;
        this.OCCURENCE = OCCURENCE;
        this.PHOTOTRAVAIL = PHOTOTRAVAIL;
    }

    public String getCINOUVRIER() {
        return CINOUVRIER;
    }

    public void setCINOUVRIER(String CINOUVRIER) {
        this.CINOUVRIER = CINOUVRIER;
    }

    public int getIDMETIER() {
        return IDMETIER;
    }

    public void setIDMETIER(int IDMETIER) {
        this.IDMETIER = IDMETIER;
    }

    public String getNOMVILLE() {
        return NOMVILLE;
    }

    public void setNOMVILLE(String NOMVILLE) {
        this.NOMVILLE = NOMVILLE;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public String getIDTRAVAIL() {
        return IDTRAVAIL;
    }

    public void setIDTRAVAIL(String IDTRAVAIL) {
        this.IDTRAVAIL = IDTRAVAIL;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getADRESSE() {
        return ADRESSE;
    }

    public void setADRESSE(String ADRESSE) {
        this.ADRESSE = ADRESSE;
    }

    public byte[] getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(byte[] PHOTO) {
        this.PHOTO = PHOTO;
    }

    public String getDISPONIBILITE() {
        return DISPONIBILITE;
    }

    public void setDISPONIBILITE(String DISPONIBILITE) {
        this.DISPONIBILITE = DISPONIBILITE;
    }

    public int getOCCURENCE() {
        return OCCURENCE;
    }

    public void setOCCURENCE(int OCCURENCE) {
        this.OCCURENCE = OCCURENCE;
    }

    public byte[] getPHOTOTRAVAIL() {
        return PHOTOTRAVAIL;
    }

    public void setPHOTOTRAVAIL(byte[] PHOTOTRAVAIL) {
        this.PHOTOTRAVAIL = PHOTOTRAVAIL;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
