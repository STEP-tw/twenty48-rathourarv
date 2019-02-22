(ns twenty48.core
  (:gen-class))

(def group-by-identity (partial partition-by identity))
(def append-zero (comp (partial take 4) #(concat % (repeat 0))))
(def transpose (comp (partial apply list) (partial apply mapv list)))

(defn reducer
  [result list]
  (merge result (map (partial apply +) (partition-all 2 list))))

(def move-left (comp flatten (partial reduce reducer '()) group-by-identity (partial remove zero?)))

; "Moves an entire grid to the right"
(def move-grid-right (partial map (comp reverse append-zero move-left)))

; "Moves an entire grid to the left"
(def move-grid-left (partial map (comp append-zero reverse move-left)))

; "Moves an entire grid down"
(def move-grid-down (comp transpose move-grid-right transpose))

; "Moves an entire grid up"
(def move-grid-up (comp transpose move-grid-left transpose))
