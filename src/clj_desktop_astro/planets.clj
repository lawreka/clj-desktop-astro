(ns clj-desktop-astro.planets
  (:require [clojure2d.core :as c2d]
            [clj-desktop-astro.trig :as trig]
            [ephemeris.core :as ephemeris]
            [fastmath.core :as math]
            [clj-desktop-astro.chart :as chart]))

(def sun-symbol (c2d/load-image "resources/sun.png"))
(def merc-symbol (c2d/load-image "resources/mercury.png"))
(def venus-symbol (c2d/load-image "resources/venus.png"))
(def moon-symbol (c2d/load-image "resources/moon.png"))
(def mars-symbol (c2d/load-image "resources/mars.png"))
(def jupiter-symbol (c2d/load-image "resources/jupiter.png"))
(def saturn-symbol (c2d/load-image "resources/saturn.png"))
(def neptune-symbol (c2d/load-image "resources/neptune.png"))
(def uranus-symbol (c2d/load-image "resources/uranus.png"))
(def pluto-symbol (c2d/load-image "resources/pluto.png"))

(def planet-symbols [sun-symbol merc-symbol venus-symbol moon-symbol mars-symbol jupiter-symbol saturn-symbol neptune-symbol uranus-symbol pluto-symbol])
(def planet-keys [:Sun :Mercury :Venus :Moon :Mars :Jupiter :Saturn :Neptune :Uranus :Pluto])
(def planet-names ["Sun " "Mercury " "Venus " "Moon " "Mars " "Jupiter " "Saturn " "Neptune " "Uranus " "Pluto "])
(def planet-legend-locs [20 40 60 80 100])
(def planet-map (zipmap planet-keys planet-symbols))
(def planet-legend-map (zipmap planet-keys planet-names))
(def planet-legend-left-map (zipmap (take 5 planet-keys) planet-legend-locs))
(def planet-legend-right-map (zipmap (take-last 5 planet-keys) planet-legend-locs))

(def symbol-size 30)
(def symbol-spacing 15)

(defn get-planet-location
  "get planet longitude from ephemeris"
  [planet]
  (math/round (get (get (get (ephemeris/calc) :points) planet) :lon)))

(defn draw-planets
  "draw all planets"
  [canvas canvas-boundary canvas-width]
  (let [center (/ canvas-boundary 2)
        radius (* canvas-boundary 0.30)
        text-radius (* canvas-boundary 0.33)]
    (doseq [planet (keys planet-map)]
      (c2d/image canvas (get planet-map planet) (- (trig/on-circle-at-x center radius (get-planet-location planet)) symbol-spacing) (- (trig/on-circle-at-y center radius (get-planet-location planet)) symbol-spacing) symbol-size symbol-size))
    (doseq [planet (keys planet-legend-left-map)]
      (c2d/text canvas (str (get planet-legend-map planet) (chart/lon-to-sign (get-planet-location planet))) 20 (get planet-legend-left-map planet) :left))
    (doseq [planet (keys planet-legend-right-map)]
      (c2d/text canvas (str (get planet-legend-map planet) (chart/lon-to-sign (get-planet-location planet))) (- canvas-width 20) (get planet-legend-right-map planet) :right))
  canvas))
  