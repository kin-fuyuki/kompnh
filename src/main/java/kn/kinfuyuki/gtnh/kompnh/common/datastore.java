package kn.kinfuyuki.gtnh.kompnh.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.*;

import static kn.kinfuyuki.gtnh.kompnh.serverp.datapath;

public class datastore {
    public final String ID;
    public final String root;
    public static final String emptysector="                ";
    public static final char datastartflag='0',datacontinueflag='1',dataendflag='2',dataemptyflag=' ';
    public Map<String, Map<String,long[]>> indices=new HashMap<>(){};
    public datastore(String storageid){
        ID=storageid;
        root=datapath + "/" + ID + "/root";
        if (indices.isEmpty()){
            try {
                updateindices();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class packet{
        public long amount;
        public String ID;
    }
    public void updateindices() throws Exception {
        File idxfile=new File(root+"/.idx");
        if (!idxfile.createNewFile()){

        }
        else{
            File rootfolder=new File(root);
            File[] directories=rootfolder.listFiles(File::isDirectory);
            assert directories != null;
            for (File directory : directories) {
                long [] startingsectors=findsectorsbyflag(directory,'0');
                long [] endingsectors=findsectorsbyflag(directory,'2');

            }
        }
    }
    public packet fetch(String str,int amount){
        // 0=mod 1=item 2=damage 3=nbt
        String[] path=str.split("@",4);


        return null;
    }
    private long[] findsectorsbyflag(File path,char flag) throws Exception {
        RandomAccessFile file=new RandomAccessFile(path,"r");
        long length= file.length();
        long [] indices=new long[Math.toIntExact(length / 16)];
        int amount=0;
        for (long i = 0; i < length; i+=16) {
            file.seek(i);
            if (file.read()==flag){
                indices[amount]=i;
                amount+=1;
            }
        }
        indices=Arrays.copyOf(indices,amount);
        file.close();
        return indices;
    }
    // for single reads, fast for when you need to read single chunks of the file.
    private static String readData(File path, long sectorstart, long sectorend) throws Exception{
        String data="";
        RandomAccessFile file=new RandomAccessFile(path,"r");

        for (long i = sectorstart; i < sectorend;i+=16) {

        }
        file.close();
        return data;
    }
    // for multiple reads, fast for multiple reads of the file with a single IO call.
    private static String[] readDataArray (File path, long[] sectorstarts, long[] sectorends) throws Exception {
        String[] datas = new String[sectorstarts.length];
        RandomAccessFile file=new RandomAccessFile(path,"r");
        for (int i = 0; i < sectorstarts.length; i++) {
            long sectorstart=sectorstarts[i];
            long sectorend=sectorends[i];
            String data="";
                for (long j = sectorstart; j < sectorend;j+=16) {
                    char[] buffer=new char[15];//ignoring flag
                }
        }
        file.close();
        return datas;
    }
    private void writeSector(long sector, String data){

    }

    /*
    * path structure
    *
    * <world>/kompnh/<id>
    * |- root
    * |       |- <modname>
    * |- meta
    * |       |- <modname>'<itemname>
    * */

    /*
    * .idx sample structure:
    * <itemname> <starting sector> <ending sector>
    * */
    /*
    * .store sample structure:
    * <damage> <amount> <nbt>
    * */

    /* THIS IS STILL A PLANNED FEATURE FOR FUTURE OPTIMIZATION, IT WILL ONLY STORE AMOUNT LONGS AND SEARCH BY INDEX (aka bytes on pos*8 to pos*8+3)
    * meta item sample structure (this will only accept items without nbt data):
    * <amount of item with damage 0><amount of item with damage 1><amount of item with damage 2>
    * ...
    * */

}
