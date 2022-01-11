/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cio.kurumsal.JavaFX_PageReplacement_Project;

/**
 *
 * @author Hosam
 */
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Fifo {

    String output = "\n";

    // Method to find page faults using FIFO
    public String pageFaults(int pages[], int n, int capacity) {
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not
        HashSet<Integer> s = new HashSet<>(capacity);

        // To store the pages in FIFO manner
        Queue<Integer> indexes = new LinkedList<>();

        // Start from initial page
        int page_faults = 0;
        for (int i = 0; i < n; i++) {
            // Check if the set can hold more pages
            if (s.size() < capacity) {
                // Insert it into set if not present
                // already which represents page fault
                if (!s.contains(pages[i])) {
                    s.add(pages[i]);
                    output += "add page " + pages[i] + "\n";
                    // increment page fault
                    page_faults++;

                    // Push the current page into the queue
                    indexes.add(pages[i]);
                }
            } // If the set is full then need to perform FIFO
            // i.e. remove the first page of the queue from
            // set and queue both and insert the current page
            else {
                // Check if current page is not already
                // present in the set
                if (!s.contains(pages[i])) {
                    //Pop the first page from the queue
                    int val = indexes.peek();

                    indexes.poll();

                    // Remove the indexes page
                    s.remove(val);

                    // insert the current page
                    s.add(pages[i]);

                    // push the current page into
                    // the queue
                    indexes.add(pages[i]);
                    output += "remove page " + val + " - add page " + pages[i] + "\n";
                    // Increment page faults
                    page_faults++;
                }
            }
        }

        output += page_faults;
        return output;
    }

    // Method to find page faults using FIFO
    public String pageFaults(String pages[], int n, int capacity) {
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not
        HashSet<Integer> s = new HashSet<>(capacity);

        // To store the pages in FIFO manner
        Queue<Integer> indexes = new LinkedList<>();

        // Start from initial page
        int page_faults = 0;
        for (int i = 0; i < n; i++) {
            // Check if the set can hold more pages
            if (s.size() < capacity) {
                // Insert it into set if not present
                // already which represents page fault
                if (!s.contains(Integer.parseInt(pages[i]))) {
                    s.add(Integer.parseInt(pages[i]));
                    output += "add page " + pages[i] + "\n";
                    // increment page fault
                    page_faults++;

                    // Push the current page into the queue
                    indexes.add(Integer.parseInt(pages[i]));
                }
            } // If the set is full then need to perform FIFO
            // i.e. remove the first page of the queue from
            // set and queue both and insert the current page
            else {
                // Check if current page is not already
                // present in the set
                if (!s.contains(Integer.parseInt(pages[i]))) {
                    //Pop the first page from the queue
                    int val = indexes.peek();

                    indexes.poll();

                    // Remove the indexes page
                    s.remove(val);

                    // insert the current page
                    s.add(Integer.parseInt(pages[i]));

                    // push the current page into
                    // the queue
                    indexes.add(Integer.parseInt(pages[i]));
                    output += "remove page " + val + " - add page " + pages[i] + "\n";
                    // Increment page faults
                    page_faults++;
                }
            }
        }

        //output += page_faults;
        output += "Total page faults were " + page_faults + "\n";
        return output;
    }    
}
