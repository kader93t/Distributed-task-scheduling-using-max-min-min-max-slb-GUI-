/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordonnacement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Drmz
 */
public class Xmin {
    public static HashMap<String /*machin*/, HashMap<String /*tache*/, Integer /*tempExecution*/> /*taches*/> schuduler1;
    ArrayList<HashMap<String,Integer>>  taches ;
    public Xmin(){
    schuduler1 = new HashMap<>();
    taches = new ArrayList<>();
     int i = 0;
     for(String machine : Scheduler.schuduler.keySet()){
             taches.add(new HashMap<>());
    
             for(String tache : Scheduler.schuduler.get(machine).keySet()){
                 taches.get(i).put(tache, Scheduler.schuduler.get(machine).get(tache));
             }
             schuduler1.put(machine,taches.get(i));
             i++;
         }    
   
        //System.out.println("brahim brahim brahim ==== > " + schuduler1);
    } 
    public static String getMin(HashMap<String, Integer> taches) {
        
        String min = taches.keySet().toArray()[0].toString();
        //System.out.println(taches);
        
        for (String tache : taches.keySet()) {
            //  System.out.println("tachesss     "+ taches);
            if (taches.get(tache) < taches.get(min)) {
                min = tache;
            }
        }
        return min;
    }

    public static String getMax(HashMap<String, Integer> taches) {
        String max = taches.keySet().toArray()[0].toString();
        for (String tache : taches.keySet()) {
            if (taches.get(tache) > taches.get(max)) {
                max = tache;
            }
        }
        return max;
    }

    public boolean isEmpty() {
        for (String machine : Scheduler.schuduler.keySet()) {
            if (Scheduler.schuduler.get(machine).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void xMin(String type) {
        //System.out.println("machinssss"+Scheduler.machines.size());
      
        while (isEmpty()) {
           
            //System.out.println(schuduler
            // resultatEtap1 est soit l'ensemble de min ou de max (type)
           // System.out.println(Scheduler.machines.size() + " = = "+Scheduler.machines);
            HashMap<String, Integer> resultatEtap1 = new HashMap<>();

            if (type.equalsIgnoreCase("min")) {
                for (String machine : Scheduler.schuduler.keySet()) {
                    //System.out.println(schuduler.get(machine));
                    resultatEtap1.put(getMin(Scheduler.schuduler.get(machine)), Scheduler.schuduler.get(machine).get(getMin(Scheduler.schuduler.get(machine))));

                }
            } else if (type.equalsIgnoreCase("max")) {
                for (String machine : Scheduler.schuduler.keySet()) {
                    //System.out.println(schuduler.get(machine));

                    resultatEtap1.put(getMax(Scheduler.schuduler.get(machine)), Scheduler.schuduler.get(machine).get(getMax(Scheduler.schuduler.get(machine))));

                }
            }
            //   System.out.println(resultatEtap1);
            //   System.out.println(min);
            
         ///   System.out.println("step == > " + resultatEtap1);
            String tache ="";
            if (type.equalsIgnoreCase("max")) {
                tache = getMax(resultatEtap1);
            } else if (type.equalsIgnoreCase("min")) {
              //  System.out.println("hey    " +resultatEtap1);
                tache = getMin(resultatEtap1);
            }
            Integer minExecution = 999999999;
            String machineExecute = "";
            //System.out.println("lllllllllllllllllllllll");
            for (String machine : Scheduler.schuduler.keySet()) {
                int tempExecution = Scheduler.schuduler.get(machine).get(tache);
                //System.out.println(machine + " =========> " );
              //  System.out.println("hhhhh "+machine);
                //System.out.println("hhhhh "+ Scheduler.machines);
               // System.out.println("hklajdklfj"+ Scheduler.machines.size());
                int tempExecuter = Scheduler.machines.get(machine);
                int c = tempExecuter + tempExecution;
                if (c < minExecution) {
                    minExecution = c;
                    machineExecute = machine;
                }
            }
            //  System.out.println("kkkkkkkkkkkkkk");
            HashMap<String, Integer> tacheAAjouter = new HashMap<>();
            tacheAAjouter.put(tache, Scheduler.schuduler.get(machineExecute).get(tache));
            if (Scheduler.FileMachine.get(machineExecute) != null) {
                Scheduler.FileMachine.get(machineExecute).put(tache, Scheduler.schuduler.get(machineExecute).get(tache));
            } else {
                Scheduler.FileMachine.put(machineExecute, tacheAAjouter);
            }
            
            // System.out.println("ssssssssssss");
            Scheduler.machines.put(machineExecute, minExecution);
           for (String machine : Scheduler.schuduler.keySet()) {
                Scheduler.schuduler.get(machine).remove(tache);
            }
        }
        for (String machin : Scheduler.FileMachine.keySet()) {
            //System.out.println(machin + " " + Scheduler.FileMachine.get(machin));
        }
        Scheduler.schuduler= schuduler1;
       // System.out.println("drdrdtdtdtdtdtdtdtdtd = " + Scheduler.schuduler);
        
       /* HashMap<String ,Object> res = new HashMap<>();
        res.put("file", Scheduler.FileMachine);
        res.put("machine",Scheduler.machines);
        return res;*/
    }
}
