/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordonnacement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Drmz
 */
public class Slb {
    
    public HashMap<String, Integer> machines;
    public HashMap<String, HashMap<String, Integer>> FileMachine;
    ArrayList<HashMap<String, Integer>> taches;
    int i = 0;
    
    public String getMin() {
        HashMap<String, Integer> taches = new HashMap<>();
        for(String machine :Scheduler.schuduler.keySet()){
            taches.put(machine,machines.get(machine));
        }
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
   
    public String getMax(HashMap<String, HashMap<String, Integer>> FileMachine) {
         
        HashMap<String, Integer> taches = new HashMap<>();
        for(String tach : FileMachine.keySet()){
            taches.put(tach, this.machines.get(tach));
        }
        String max = taches.keySet().toArray()[0].toString();
        for (String tache : taches.keySet()) {
            
            if (taches.get(tache) > taches.get(max)) {
                max = tache;
            }
        }
        return max;
    }

    public Slb() {
        //System.out.println("File Machine avant ====> "+ Scheduler.FileMachine);
        taches = new ArrayList<>();
        FileMachine = new HashMap<>();
        machines = new HashMap<>();
       
        // System.out.println("sche   "+Scheduler.machines);
        for (String machine : Scheduler.machines.keySet()) {
            machines.put(machine, Scheduler.machines.get(machine));
                
//System.out.println("ddddd" + machines);
        }

        for (String machine : Scheduler.FileMachine.keySet()) {
            taches.add(new HashMap<>());
            for (String tache : Scheduler.FileMachine.get(machine).keySet()) {
                taches.get(i).put(tache, Scheduler.FileMachine.get(machine).get(tache));
            }
            FileMachine.put(machine, taches.get(i));
            i++;
        }
    }

    public Integer getPositivValue(Integer value) {
        if (value < 0) {
            return -value;
        }
        return value;
    }

    public void slb() {
        // System.out.println("eami l;akldjf ;j ;j "+ Scheduler.schuduler);
        // System.out.println(machines);
        Boolean estComplete = false;
        //System.out.println(" machines " + machines);
        while (!estComplete) {
            //   System.out.println("machins " +machines);
            String minMachine = getMin();
            String maxMachine = getMax(FileMachine);

            /* System.out.println("ka ===============>" +machines.get(minMachine));
            System.out.println("min machine =>"+FileMachine.get(minMachine)+" temp "+machines.get(minMachine));
            System.out.println("max machine =>"+FileMachine.get(maxMachine)+" temp "+machines.get(maxMachine));
            System.out.println("original min => "+Scheduler.schuduler.get(minMachine));
            System.out.println("original max =>"+Scheduler.schuduler.get(maxMachine));*/
            HashMap<String, Integer> aAffecter = new HashMap<>();
            int tempExecutionDeLaMachineMax = machines.get(maxMachine);
            //System.out.println(tempExecutionDeLaMachineMax);
            int tempExecutionDeLaMachineMin = machines.get(minMachine);
            //System.out.println(tempExecutionDeLaMachineMin);
            int tempExecutionDeLaTache;
            int diffMachineMaxEtTache;
            int someMachineMinEtTache;
            int meilleurTemp = 999999999;
            String tacheAPermuter = null;
            // 
            //  System.out.println("File Machine === > "+ FileMachine);
            //  System.out.println(maxMachine +" "+FileMachine);
            System.out.println("machine  " + maxMachine);
            System.out.println("machines " + machines.get(maxMachine));
            System.out.println("taches " + FileMachine.get(maxMachine));
            
            for (String tache : FileMachine.get(maxMachine).keySet()) {
                tempExecutionDeLaTache = FileMachine.get(maxMachine).get(tache);
                diffMachineMaxEtTache = tempExecutionDeLaMachineMax - tempExecutionDeLaTache;
                System.out.println( minMachine +"           " +tache + "   " + Scheduler.schuduler );

                someMachineMinEtTache = tempExecutionDeLaMachineMin + Scheduler.schuduler.get(minMachine).get(tache);

                if (diffMachineMaxEtTache > tempExecutionDeLaMachineMin && someMachineMinEtTache < tempExecutionDeLaMachineMax) {
                    if (getPositivValue(diffMachineMaxEtTache - someMachineMinEtTache) < meilleurTemp) {
                        tacheAPermuter = tache;
                    }

                }
            }

            if (tacheAPermuter != null) {
                // System.out.println(tacheAPermuter);
                // System.out.println("hhhhhhhh=>" + minMachine +"+++++++++++++"+tacheAPermuter+"-------"+ FileMachine );
                System.out.println("permuted ===> " + tacheAPermuter);
                machines.put(minMachine, machines.get(minMachine) + Scheduler.schuduler.get(minMachine).get(tacheAPermuter));

                machines.put(maxMachine, machines.get(maxMachine) - FileMachine.get(maxMachine).remove(tacheAPermuter));
                try {
                    FileMachine.get(minMachine).put(tacheAPermuter, Scheduler.schuduler.get(minMachine).get(tacheAPermuter));
                } catch (NullPointerException e) {
                    FileMachine.put(minMachine, new HashMap<>());
                    FileMachine.get(minMachine).put(tacheAPermuter, Scheduler.schuduler.get(minMachine).get(tacheAPermuter));

                }
            } else {
                estComplete = true;
                // System.out.println(estComplete);
            }
        }

//        System.out.println("SLB => " + machines);
        //       System.out.println("SLB =>" + FileMachine);
    }

}
