(ns clj-desktop-astro.trig
  (:require [fastmath.core :as math]))

(defn on-circle-at-x
  [center radius degrees]
  (+ center (* radius (math/sin (math/radians degrees)))))

(defn on-circle-at-y
  [center radius degrees]
  (+ center (* radius (math/cos (math/radians degrees)))))
