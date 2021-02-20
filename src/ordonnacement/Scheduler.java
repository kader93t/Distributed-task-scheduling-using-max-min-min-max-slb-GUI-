/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordonnacement;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Drmz
 */
public class Scheduler {

    public static HashMap<String /*machin*/, HashMap<String /*tache*/, Integer /*tempExecution*/> /*taches*/> schuduler;
    public static HashMap<String/*machin*/, Integer /*le temp executer sur la machine */> machines;
    public static HashMap<String, HashMap<String, Integer>> FileMachine;

    public Scheduler() {
        schuduler = new HashMap<>();

        HashMap<String, Integer> tache1 = new HashMap<>();
        HashMap<String, Integer> tache2 = new HashMap<>();
        HashMap<String, Integer> tache3 = new HashMap<>();


        tache1.put("t1", 12);
        tache1.put("t2", 45);
        
        tache1.put("t3", 67);
        tache1.put("t4", 99);
        tache1.put("t5", 6);
        tache1.put("t6", 34);
        tache1.put("t7", 65);
        tache1.put("t8", 88);
        tache1.put("t9", 145);
        tache1.put("t10", 16);
        tache1.put("t11", 8);
        tache1.put("t12", 766);
        tache1.put("t13", 45);
        schuduler.put("m1", tache1);

        tache2.put("t1", 54);
        tache2.put("t2", 26);
        tache2.put("t3", 71);
        tache2.put("t4", 86);
        tache2.put("t5", 62);
        tache2.put("t6", 22);
        tache2.put("t7", 100);
        tache2.put("t8", 75);
        tache2.put("t9", 48);
        tache2.put("t10", 14);
        tache2.put("t11", 677);
        tache2.put("t12", 93);
        tache2.put("t13", 17);
        schuduler.put("m2", tache2);

        tache3.put("t1", 5);
        tache3.put("t2", 33);
        tache3.put("t3", 49);
        tache3.put("t4", 70);
        tache3.put("t5", 11);
        tache3.put("t6", 87);
        tache3.put("t7", 63);
        tache3.put("t8", 15);
        tache3.put("t9", 44);
        tache3.put("t10", 38);
        tache3.put("t11", 140);
        tache3.put("t12", 10);
        tache3.put("t13", 189);
        schuduler.put("m3", tache3);
        //System.out.println("SSSSSS bbb    " + schuduler);
        machines = new HashMap<>();
        FileMachine = new HashMap<>();
    }

}
