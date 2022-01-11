/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cio.kurumsal.JavaFX_PageReplacement_Project;

/**
 *
 * @author Hosam
 */
public class ClockReplacement {

    String output = "\n";

    public String ClockPaging(int frames, int ref_len, String processes_reference[]) {
        int pointer = 0, hit = 0, fault = 0;
        int buffer[][];
        //String reference[];
        int mem_layout[][];
        int used_layout[][];

        //reference = new String[ref_len];
        mem_layout = new int[ref_len][frames];
        used_layout = new int[ref_len][frames];
        buffer = new int[frames][2];
        for (int j = 0; j < frames; j++) {
            buffer[j][0] = -1;
            buffer[j][1] = 0;
        }
        //reference = processes_reference;
        System.out.println();
        for (int i = 0; i < ref_len; i++) {
            int search = -1;
            for (int j = 0; j < frames; j++) {
                if (buffer[j][0] == Integer.parseInt(processes_reference[i])) {
                    search = j;
                    hit++;
                    buffer[j][1] = 1;
                    break;
                }
            }
            if (search == -1) {

                while (buffer[pointer][1] == 1) {
                    buffer[pointer][1] = 0;
                    pointer++;
                    if (pointer == frames) {
                        pointer = 0;
                    }
                }
                buffer[pointer][0] = Integer.parseInt(processes_reference[i]);
                buffer[pointer][1] = 1;
                fault++;
                pointer++;
                if (pointer == frames) {
                    pointer = 0;
                }
            }
            for (int j = 0; j < frames; j++) {
                mem_layout[i][j] = buffer[j][0];
                used_layout[i][j] = buffer[j][1];
            }
        }

        for (int i = 0; i < frames; i++) {
            for (int j = 0; j < ref_len; j++) {
                System.out.printf("%3d %d ", mem_layout[j][i], used_layout[j][i]);
                output += mem_layout[j][i] + "     " + used_layout[j][i];
            }
            output += "\n";
            System.out.println();
        }
        output += "\n";

        output += "The number of Hits: " + hit + "\n";
        output += "Hit Ratio: " + (float) ((float) hit / ref_len) + "\n";
        output += "The number of Faults: " + fault + "\n";
        return output;
    }

}
