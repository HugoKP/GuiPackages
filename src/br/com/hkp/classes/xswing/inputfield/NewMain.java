/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hkp.classes.xswing.inputfield;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author userhugo
 */
public class NewMain implements Comparable
{
    public int v;
    
    public NewMain(int va)
    {
        v = va;
    }
     
    @Override
    public int compareTo(Object k)
    {
        NewMain n = (NewMain) k;
        if (v > n.v) return 1;
        else if (v < n.v) return -1;
        else return 0;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(v);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Integer[] arrayInt = {1,2,3};
        int[] arrayInt2 = {1,2,3};
        List <Integer> listInt = (Arrays.asList(arrayInt));
        
        listInt.set(0,10);
        
        System.out.println(listInt);
        System.out.println(Arrays.toString(arrayInt));
        
        
        
        
        NewMain[] n = new NewMain[2];
        n[0] = new NewMain(10);
        n[1] = new NewMain(1);
        
        System.out.println(n[0]);
        
        Arrays.sort(n);
        
        System.out.println(n[0]);
        
        System.out.println( Arrays.binarySearch(n,new NewMain(0)));
        // TODO code application logic here
    }
    
}
