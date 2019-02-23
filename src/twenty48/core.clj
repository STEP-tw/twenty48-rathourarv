(ns twenty48.core
  (:gen-class))

(def group-by-identity (partial partition-by identity))
(def append-zero (comp (partial take 4) #(concat % (repeat 0))))
(def transpose (comp (partial apply list) (partial apply mapv list)))

(def remove-zeroes (partial remove zero?))

(def group-in-pairs (partial mapcat (partial partition-all 2)))

(def sum-all (partial map (partial apply +)))

(def move-left (comp sum-all
                     group-in-pairs 
                     group-by-identity 
                     remove-zeroes))

; "Moves an entire grid to the right"
(def move-grid-right (partial map (comp reverse append-zero move-left)))

; "Moves an entire grid to the left"
(def move-grid-left (partial map (comp append-zero reverse move-left)))

; "Moves an entire grid down"
(def move-grid-down (comp transpose move-grid-right transpose))

; "Moves an entire grid up"
(def move-grid-up (comp transpose move-grid-left transpose))
