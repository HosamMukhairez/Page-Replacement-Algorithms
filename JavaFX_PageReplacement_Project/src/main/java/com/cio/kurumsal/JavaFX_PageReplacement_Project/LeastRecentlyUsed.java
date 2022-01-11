/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cio.kurumsal.JavaFX_PageReplacement_Project;

/**
 *
 * @author Hosam
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

class LeastRecentlyUsed {

    String output = "\n";

    // Method to find page faults using indexes
    static int pageFaults(int pages[], int n, int capacity) {
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not
        HashSet<Integer> s = new HashSet<>(capacity);

        // To store least recently used indexes
        // of pages.
        HashMap<Integer, Integer> indexes = new HashMap<>();

        // Start from initial page
        int page_faults = 0;
        for (int i = 0; i < n; i++) {
            // Check if the set can hold more pages
            if (s.size() < capacity) {
                // Insert it into set if not present
                // already which represents page fault
                if (!s.contains(pages[i])) {
                    s.add(pages[i]);

                    // increment page fault
                    page_faults++;
                }

                // Store the recently used index of
                // each page
                indexes.put(pages[i], i);
            } // If the set is full then need to perform lru
            // i.e. remove the least recently used page
            // and insert the current page
            else {
                // Check if current page is not already
                // present in the set
                if (!s.contains(pages[i])) {
                    // Find the least recently used pages
                    // that is present in the set
                    int lru = Integer.MAX_VALUE, val = Integer.MIN_VALUE;

                    Iterator<Integer> itr = s.iterator();

                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (indexes.get(temp) < lru) {
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }

                    // Remove the indexes page
                    s.remove(val);
                    //remove lru from hashmap
                    indexes.remove(val);
                    // insert the current page
                    s.add(pages[i]);

                    // Increment page faults
                    page_faults++;
                }

                // Update the current page index
                indexes.put(pages[i], i);
            }
        }

        return page_faults;
    }

    // Method to find page faults using indexes
    public String pageFaults(String pages[], int n, int capacity) {
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not
        HashSet<Integer> s = new HashSet<>(capacity);

        // To store least recently used indexes
        // of pages.
        HashMap<Integer, Integer> indexes = new HashMap<>();

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
                }

                // Store the recently used index of
                // each page
                indexes.put(Integer.parseInt(pages[i]), i);
            } // If the set is full then need to perform lru
            // i.e. remove the least recently used page
            // and insert the current page
            else {
                // Check if current page is not already
                // present in the set
                if (!s.contains(Integer.parseInt(pages[i]))) {
                    // Find the least recently used pages
                    // that is present in the set
                    int lru = Integer.MAX_VALUE, val = Integer.MIN_VALUE;

                    Iterator<Integer> itr = s.iterator();

                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (indexes.get(temp) < lru) {
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }

                    // Remove the indexes page
                    s.remove(val);
                    //remove lru from hashmap
                    indexes.remove(val);
                    // insert the current page
                    s.add(Integer.parseInt(pages[i]));

                    output += "remove page " + val + " - add page " + pages[i] + "\n";

                    // Increment page faults
                    page_faults++;
                }

                // Update the current page index
                indexes.put(Integer.parseInt(pages[i]), i);
            }
        }
        //output += page_faults;
        output += "Total page faults were " + page_faults + "\n";
        return output;
    }    
}
