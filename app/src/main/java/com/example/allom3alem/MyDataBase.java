package com.example.allom3alem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {

    //juste pour "the best practice"
    private static final String Db_Name = "Allo_l'M3lM.sqlite";
    private static final int Db_Version = 8;
    private ByteArrayOutputStream objectByteArrayOutputStream,objectByteArrayOutputStream1;
    private byte[] imageInByte,imageInByte1;

    //les attributs de base de donnes de region
    private static final  String Region_Table_Name = "REGION";
    private static final  String Region_CLN_Id = "IDREGION";
    private static final  String Region_CLN_Nom = "NOM";
    //les attributs de base de donnes de ville
    private static final  String Ville_Table_Name = "VILLE";
    private static final  String Ville_CLN_Id = "IDVILLE";
    private static final  String Ville_CLN_IdRegion = "IDREGION";
    private static final  String Ville_CLN_Nom = "NOM";
    //les attribus de base de donnnes de liste metiers
    private static final  String ListMetiers_Table_Name = "METIER";
    private static final  String ListMetiers_CLN_Id = "IDMETIER";
    private static final  String ListMetiers_CLN_Nom = "NOM";
    private static final  String ListMetiers_CLN_Logo = "LOGO";
    //les attributs de base de donnes d'Ouvriers
    private static final  String Ouvrier_Table_Name = "OUVRIER";
    private static final  String Ouvrier_CLN_CINOUVRIER = "CINOUVRIER";
    private static final  String Ouvrier_CLN_IDMETIER = "IDMETIER";
    private static final  String Ouvrier_CLN_NOMVILLE = "NOMVILLE";
    private static final  String Ouvrier_CLN_NOM = "NOM";
    private static final  String Ouvrier_CLN_IDTRAVAIL = "IDTRAVAIL";
    private static final  String Ouvrier_CLN_MOBILE = "MOBILE";
    private static final  String Ouvrier_CLN_ADRESSE = "ADRESSE";
    private static final  String Ouvrier_CLN_DESCRIPTION = "DESCRIPTION";
    private static final  String Ouvrier_CLN_RATING = "RATING";
    private static final  String Ouvrier_CLN_PHOTO = "PHOTO";
    private static final  String Ouvrier_CLN_DISPONIBILITE = "DISPONIBILITE";
    private static final  String Ouvrier_CLN_OCCURENCE = "OCCURENCE";
    private static final  String Ouvrier_CLN_PHOTOTRAVAIL = "PHOTOTRAVAIL";
    private static final  String Ouvrier_CLN_MOTDEPSSE = "MOTDEPASSE";
    //les attributs de base de donnes du Client
    //les attributs de base de donnes du Client
    private static final  String Client_Table_Name = "Client";
    private static final  String Client_CLN_MOBILE = "MOBILE";
    private static final  String Client_CLN_MOTDEPSSE = "MOTDEPASSE";
    private static final  String CLIENT_CLN_1 = "CINCLIENT";
    private static final  String CLIENT_CLN_2 = "NOM";
    private static final  String CLIENT_CLN_3 = "MOBILE";
    private static final  String CLIENT_CLN_4 = "MOTDEPASSE";


    public MyDataBase(Context context){
        super(context,Db_Name,null,Db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE " +Region_Table_Name+" ("+Region_CLN_Id+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
              // Region_CLN_Nom+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS region");
        db.execSQL("DROP TABLE IF EXISTS ville");
        onCreate(db);
    }

    public long getRegionCount(){
        SQLiteDatabase db = getReadableDatabase();

        return DatabaseUtils.queryNumEntries(db,Ouvrier_Table_Name);
    }

    //la methode de select (sans condition)
    public ArrayList<String> getAllRegion(){
        ArrayList<Region> regions = new ArrayList<>();
        ArrayList<String> regionsString = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Region_Table_Name,null);
        //le code pour travailler avec le cursor:
        //check le cursor si il contient les datas
        if(cursor!=null && cursor.moveToFirst()){
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Region_CLN_Id));
                @SuppressLint("Range") String model = cursor.getString(cursor.getColumnIndex(Region_CLN_Nom));

                Region r = new Region(id,model);
                regions.add(r);
            }while(cursor.moveToNext());
            cursor.close();
        }
        for(int i = 0;i<regions.size();i++)
        {
            regionsString.add(regions.get(i).getNom());
        }

        return regionsString;

    }

    //la methode de serach
    public ArrayList<String> getVille(int idRef){
        ArrayList<Ville> villes = new ArrayList<>();
        ArrayList<String> villesString = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Ville_Table_Name+" Where "+Ville_CLN_IdRegion+"="+idRef,null);
        //le code pour travailler avec le cursor:
        //check le cursor si il contient les datas
        if(cursor!=null && cursor.moveToFirst()){
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Ville_CLN_Id));
                @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex(Ville_CLN_Nom));

                Ville v = new Ville(nom,id);
                villes.add(v);
            }while(cursor.moveToNext());
            cursor.close();
        }
        for(int i = 0;i<villes.size();i++)
        {
            villesString.add(villes.get(i).getNom());
        }

        return villesString;
    }

    public ArrayList<Metiers> getListMetiers()
    {
        ArrayList<Metiers> metiers = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ListMetiers_Table_Name,null);

        if(cursor!=null && cursor.moveToFirst()){
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ListMetiers_CLN_Id));
                @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex(ListMetiers_CLN_Nom));
                @SuppressLint("Range") byte[] logo = cursor.getBlob(cursor.getColumnIndex(ListMetiers_CLN_Logo));

                Metiers lm = new Metiers(id,nom,logo);
                metiers.add(lm);
            }while(cursor.moveToNext());
            cursor.close();
        }

        return metiers;
    }

    public ArrayList<Ouvrier> getOuvriers()
    {
        ArrayList<Ouvrier> ouvriers = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Ouvrier_Table_Name,null);

        if(cursor!=null && cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String cinOuvrier = cursor.getString(cursor.getColumnIndex(Ouvrier_CLN_CINOUVRIER));
                @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex(ListMetiers_CLN_Nom));
                @SuppressLint("Range") double rating = cursor.getDouble(cursor.getColumnIndex(Ouvrier_CLN_RATING));
                @SuppressLint("Range") int occurence = cursor.getInt(cursor.getColumnIndex(Ouvrier_CLN_OCCURENCE));
                @SuppressLint("Range") String disponibilite = cursor.getString(cursor.getColumnIndex(Ouvrier_CLN_DISPONIBILITE));
                @SuppressLint("Range") int idmetier = cursor.getInt(cursor.getColumnIndex(Ouvrier_CLN_IDMETIER));
                @SuppressLint("Range") String nomville = cursor.getString(cursor.getColumnIndex(Ouvrier_CLN_NOMVILLE));
                @SuppressLint("Range") byte[] photo = cursor.getBlob(cursor.getColumnIndex(Ouvrier_CLN_PHOTO));
                @SuppressLint("Range") String desc = cursor.getString(cursor.getColumnIndex(Ouvrier_CLN_DESCRIPTION));
                @SuppressLint("Range") String adr = cursor.getString(cursor.getColumnIndex(Ouvrier_CLN_ADRESSE));
                @SuppressLint("Range") String mobile = cursor.getString(cursor.getColumnIndex(Ouvrier_CLN_MOBILE));
                @SuppressLint("Range") byte[] photoDeTravail = cursor.getBlob(cursor.getColumnIndex(Ouvrier_CLN_PHOTOTRAVAIL));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(Ouvrier_CLN_MOTDEPSSE));

                Ouvrier lm = new Ouvrier(cinOuvrier,nom,rating,occurence,disponibilite,idmetier,nomville,photo,desc,adr,mobile,photoDeTravail,password);
                ouvriers.add(lm);
            }while(cursor.moveToNext());
            cursor.close();
        }

        return ouvriers;
    }


    @SuppressLint("Range")
    public boolean checkUserExist(String username, String password){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorClient = db.rawQuery("SELECT * FROM "+Client_Table_Name,null);
        Cursor cursorOuvrier = db.rawQuery("SELECT * FROM "+Ouvrier_Table_Name,null);

        int count = 0;

        if(cursorClient.moveToFirst()) {
            do {
                if(cursorClient.getString(cursorClient.getColumnIndex(Client_CLN_MOBILE)).equals(username) && cursorClient.getString(cursorClient.getColumnIndex(Client_CLN_MOTDEPSSE)).equals(password))
                {    count++;   }
            } while (cursorClient.moveToNext());
        }


        if(cursorOuvrier.moveToFirst()) {
            do {
                if(cursorOuvrier.getString(cursorOuvrier.getColumnIndex(Ouvrier_CLN_MOBILE)).equals(username) && cursorOuvrier.getString(cursorOuvrier.getColumnIndex(Ouvrier_CLN_MOTDEPSSE)).equals(password))
                {    count++;   }
            } while (cursorOuvrier.moveToNext());
        }


        cursorClient.close();
        cursorOuvrier.close();

        return count > 0;
    }

    //La méthode de modification des champs de texte
    public boolean updateRecord(String cinOuvrier,String nomOuvrier,String desc,String adr,String dispo,String telephone,String password,String Npassword) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(!telephone.isEmpty()) {

            contentValues.put("MOBILE",telephone);
        }

        if(Npassword.isEmpty()) {

            contentValues.put("MOTDEPASSE",password);
        }
        else{

            contentValues.put("MOTDEPASSE",Npassword);
        }

        contentValues.put("NOM",nomOuvrier);
        contentValues.put("DESCRIPTION",desc);
        contentValues.put("ADRESSE",adr);
        contentValues.put("DISPONIBILITE",dispo);
        //contentValues.put("MOBILE",telephone);
        //contentValues.put("MOTDEPASSE",Npassword);


        Cursor cursor = db.rawQuery("SELECT * FROM OUVRIER WHERE CINOUVRIER = ?",new String[] {cinOuvrier});

        if(cursor.getCount()>0) {

            long result = db.update(Ouvrier_Table_Name,contentValues,"CINOUVRIER=?",new String[] {cinOuvrier});

            if(result == -1) {

                return false;

            }else {

                return true;
            }

        }else {

            return false;
        }
    }

    //La méthode de modification des ouvriers avec image de profil
    public boolean updateRecordWithPhotoProfil(String cinOuvrier,String nomOuvrier,String desc,String adr,String dispo,String telephone,String password,String Npassword,Bitmap photoProfil) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        objectByteArrayOutputStream = new ByteArrayOutputStream();
        photoProfil.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

        imageInByte = objectByteArrayOutputStream.toByteArray();

        if(!telephone.isEmpty()) {

            contentValues.put("MOBILE",telephone);
        }

        if(Npassword.isEmpty()) {

            contentValues.put("MOTDEPASSE",password);
        }
        else{

            contentValues.put("MOTDEPASSE",Npassword);
        }

        contentValues.put("NOM",nomOuvrier);
        contentValues.put("DESCRIPTION",desc);
        contentValues.put("ADRESSE",adr);
        contentValues.put("DISPONIBILITE",dispo);
        contentValues.put("PHOTO",imageInByte);
        //contentValues.put("MOBILE",telephone);
        //contentValues.put("MOTDEPASSE",Npassword);


        Cursor cursor = db.rawQuery("SELECT * FROM OUVRIER WHERE CINOUVRIER = ?",new String[] {cinOuvrier});

        if(cursor.getCount()>0) {

            long result = db.update(Ouvrier_Table_Name,contentValues,"CINOUVRIER=?",new String[] {cinOuvrier});

            if(result == -1) {

                return false;

            }else {

                return true;
            }

        }else {

            return false;
        }



    }

    //La méthode de modification des ouvriers avec image de travail
    public boolean updateRecordWithPhotoTravail(String cinOuvrier,String nomOuvrier,String desc,String adr,String dispo,String telephone,String password,String Npassword,Bitmap photoTravail) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        objectByteArrayOutputStream = new ByteArrayOutputStream();

        photoTravail.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

        imageInByte = objectByteArrayOutputStream.toByteArray();

        if(!telephone.isEmpty()) {

            contentValues.put("MOBILE",telephone);
        }

        if(Npassword.isEmpty()) {

            contentValues.put("MOTDEPASSE",password);
        }
        else{

            contentValues.put("MOTDEPASSE",Npassword);
        }

        contentValues.put("NOM",nomOuvrier);
        contentValues.put("DESCRIPTION",desc);
        contentValues.put("ADRESSE",adr);
        contentValues.put("DISPONIBILITE",dispo);
        contentValues.put("PHOTOTRAVAIL",imageInByte);
        //contentValues.put("MOBILE",telephone);
        //contentValues.put("MOTDEPASSE",Npassword);


        Cursor cursor = db.rawQuery("SELECT * FROM OUVRIER WHERE CINOUVRIER = ?",new String[] {cinOuvrier});

        if(cursor.getCount()>0) {

            long result = db.update(Ouvrier_Table_Name,contentValues,"CINOUVRIER=?",new String[] {cinOuvrier});

            if(result == -1) {

                return false;

            }else {

                return true;
            }

        }else {

            return false;
        }
    }

    //La méthode pour modifier les deux photos
    public boolean updateRecordWithBoth(String cinOuvrier,String nomOuvrier,String desc,String adr,String dispo,String telephone,String password,String Npassword,Bitmap photoProfil,Bitmap photoTravail) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        objectByteArrayOutputStream = new ByteArrayOutputStream();
        objectByteArrayOutputStream1 = new ByteArrayOutputStream();

        photoProfil.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);
        photoTravail.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream1);


        imageInByte = objectByteArrayOutputStream.toByteArray();
        imageInByte1 = objectByteArrayOutputStream1.toByteArray();

        if(!telephone.isEmpty()) {

            contentValues.put("MOBILE",telephone);
        }

        if(Npassword.isEmpty()) {

            contentValues.put("MOTDEPASSE",password);
        }
        else{

            contentValues.put("MOTDEPASSE",Npassword);
        }

        contentValues.put("NOM",nomOuvrier);
        contentValues.put("DESCRIPTION",desc);
        contentValues.put("ADRESSE",adr);
        contentValues.put("DISPONIBILITE",dispo);
        contentValues.put("PHOTO",imageInByte);
        contentValues.put("PHOTOTRAVAIL",imageInByte1);
        //contentValues.put("MOBILE",telephone);
        //contentValues.put("MOTDEPASSE",Npassword);


        Cursor cursor = db.rawQuery("SELECT * FROM OUVRIER WHERE CINOUVRIER = ?",new String[] {cinOuvrier});

        if(cursor.getCount()>0) {

            long result = db.update(Ouvrier_Table_Name,contentValues,"CINOUVRIER=?",new String[] {cinOuvrier});

            if(result == -1) {

                return false;

            }else {

                return true;
            }

        }else {

            return false;
        }
    }

    /*
    public boolean updateRecordWithBoothImage(String cinOuvrier,String nomOuvrier,String desc,String adr,String dispo,String telephone,Bitmap imagePhoto,Bitmap imageTravail) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        objectByteArrayOutputStream = new ByteArrayOutputStream();
        imagePhoto.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);
        imageInByte = objectByteArrayOutputStream.toByteArray();
        contentValues.put("PHOTO",imageInByte);

        objectByteArrayOutputStream2 = new ByteArrayOutputStream();
        imageTravail.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream2);
        imageInByte2 = objectByteArrayOutputStream.toByteArray();
        contentValues.put("PHOTOTRAVAIL",imageInByte2);

        contentValues.put("NOM",nomOuvrier);
        contentValues.put("DESCRIPTION",desc);
        contentValues.put("ADRESSE",adr);
        contentValues.put("DISPONIBILITE",dispo);
        contentValues.put("MOBILE",telephone);


        Cursor cursor = db.rawQuery("SELECT * FROM OUVRIER WHERE CINOUVRIER = ?",new String[] {cinOuvrier});

        if(cursor.getCount()>0) {

            long result = db.update(Ouvrier_Table_Name,contentValues,"CINOUVRIER=?",new String[] {cinOuvrier});

            if(result == -1) {

                return false;

            }else {

                return true;
            }

        }else {

            return false;
        }
    }

     */

    //la methode pour modifier l'image de profil
    public boolean updateRecord2(String cinOuvrier, Bitmap image) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        objectByteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

        imageInByte = objectByteArrayOutputStream.toByteArray();

        contentValues.put("PHOTO",imageInByte);

        Cursor cursor = db.rawQuery("SELECT * FROM OUVRIER WHERE CINOUVRIER = ?",new String[] {cinOuvrier});

        if(cursor.getCount()>0) {

            long result = db.update(Ouvrier_Table_Name,contentValues,"CINOUVRIER=?",new String[] {cinOuvrier});

            if(result == -1) {

                return false;

            }else {

                return true;
            }

        }else {

            return false;
        }
    }

    public boolean updateRecord3(String cinOuvrier, Bitmap image) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        objectByteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

        imageInByte = objectByteArrayOutputStream.toByteArray();

        contentValues.put("PHOTOTRAVAIL",imageInByte);

        Cursor cursor = db.rawQuery("SELECT * FROM OUVRIER WHERE CINOUVRIER = ?",new String[] {cinOuvrier});

        if(cursor.getCount()>0) {

            long result = db.update(Ouvrier_Table_Name,contentValues,"CINOUVRIER=?",new String[] {cinOuvrier});

            if(result == -1) {

                return false;

            }else {

                return true;
            }

        }else {

            return false;
        }
    }

    //ajouter un client
    public  void ajouterclient( String cin,String nom,String mobile,String mdp  ){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("insert into CLIENT ("+CLIENT_CLN_1+","+CLIENT_CLN_2+","+CLIENT_CLN_3+","+CLIENT_CLN_4+") "+"values('"+cin+"','"+nom+"','"+mobile+"','"+mdp+"');");
    }

    public  void ajouterouvrier(Ouvrier ouvrier,byte[] bytes)  {

        SQLiteDatabase db = getReadableDatabase();

        double rating = 0.0;
        String dispo = "oui";
        int occurence = 0;

        ContentValues values = new ContentValues();

        values.put(Ouvrier_CLN_CINOUVRIER, ouvrier.getCINOUVRIER());
        values.put(Ouvrier_CLN_IDMETIER, ouvrier.getIDMETIER());
        values.put(Ouvrier_CLN_NOMVILLE, ouvrier.getNOMVILLE());
        values.put(Ouvrier_CLN_NOM, ouvrier.getNOM());
        values.put(Ouvrier_CLN_MOBILE, ouvrier.getMOBILE());
        values.put(Ouvrier_CLN_IDTRAVAIL,ouvrier.getIDTRAVAIL());
        values.put(Ouvrier_CLN_ADRESSE, ouvrier.getADRESSE());
        values.put(Ouvrier_CLN_DESCRIPTION, ouvrier.getDESCRIPTION());
        values.put(Ouvrier_CLN_MOTDEPSSE, ouvrier.getPASSWORD());
        values.put(Ouvrier_CLN_PHOTO, bytes);
        values.put(Ouvrier_CLN_OCCURENCE,occurence);
        values.put(Ouvrier_CLN_RATING,rating);
        values.put(Ouvrier_CLN_DISPONIBILITE,dispo);
        values.put(Ouvrier_CLN_PHOTOTRAVAIL, bytes);

        db.insert(Ouvrier_Table_Name, null, values);


    }
    //La méthode de modification de l'évaluation d'un ouvrier
    public boolean updateRecordRating(String cinOuvrier,float evaluationOuvrier,int occurenceTravail) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("RATING",evaluationOuvrier);
        contentValues.put("OCCURENCE",occurenceTravail);

        Cursor cursor = db.rawQuery("SELECT * FROM OUVRIER WHERE CINOUVRIER = ?",new String[] {cinOuvrier});

        if(cursor.getCount()>0) {

            long result = db.update(Ouvrier_Table_Name,contentValues,"CINOUVRIER=?",new String[] {cinOuvrier});

            if(result == -1) {

                return false;

            }else {

                return true;
            }

        }else {

            return false;
        }

    }
}
