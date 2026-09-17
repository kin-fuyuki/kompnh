package kn.kinfuyuki.gtnh.kompnh.common;

import java.io.File;
import java.io.RandomAccessFile;import java.util.ArrayList;import java.util.List;import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

import static kn.kinfuyuki.gtnh.kompnh.serverp.datapath;

public class datastore {
	private static final String I=".idx",S=".store";
	public final String ID;
	public final String root;
	public datastore(String storageid){
		ID=storageid;
		root=datapath + "/" + ID + "/root";
	}
	public static class packet{
		public long amount;
		public String ID;
		public long[] bytestartend;
	}
	public packet fetch (String str,int amount){
		packet result=new packet();
		
		// 0=mod 1=item 2=damage 3=nbt
		String[] path=str.split("@",4);
		try{
		if(
			!new File(root+path[0]+I).createNewFile()&&
			!new File(root+path[0]+S).createNewFile()
		){
		//byte index of the item, where its data starts and end. you need 8 hexabytes of data in .store to run out of byte indexes with a long integer, ur fine
			List<long[]> indexes=new ArrayList<>(0);
			{
			Stream<MatchResult> f1;
			{Scanner item=new Scanner(new File(root+path[0]+I));
			f1 = item.findAll(path[1]+" .*");}
			f1.forEach(
			i->{
			String[] tmp= i.group().split(" ");
			indexes.add(new long[]{Long.parseLong(tmp[1]), Long.parseLong(tmp[2])});}
			);}
			if (indexes.size()==0){return null;}
			indexes.forEach(i ->{RandomAccessFile f2=null;try{
			String block ;{f2
			=new RandomAccessFile(root+path[0]+S,"r");
			f2.seek(i[0]);
			byte[] buffer=new byte[(int)(i[1]-i[0])];
			f2.readFully(buffer);f2.close();block=new String(buffer);}
			String[] parts=block.split(" ",3);
			if (parts[0].equals(path[2])&&parts[2].equals(path[3])){
				result.amount=Long.parseLong(parts[1]);
				result.ID=this.ID;
				result.bytestartend=i;
				
			}
			}catch (Exception e){}
			
			});
			return result;
			
			
		}

		} catch (Exception e) {}
		return null;
	}




	/*
	* path structure
	*
	* <world>/kompnh/<id>
	* |- root
	* |		|- <modname>
	* |		|	|- .idx
	* |		|	|- .store
	* |- meta
	* |		|- <modname>'<itemname>
	* */

	/*
	* .idx sample structure:
	* <itemname> <byte start> <byte end>
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
