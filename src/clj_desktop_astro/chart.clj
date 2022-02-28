(ns clj-desktop-astro.chart
  (:require [clojure2d.core :as c2d]
            [clj-desktop-astro.trig :as trig]
            [fastmath.vector :as v]))

(def aries-symbol (c2d/load-image "resources/aries.png"))
(def taurus-symbol (c2d/load-image "resources/taurus.png"))
(def gemini-symbol (c2d/load-image "resources/gemini.png"))
(def cancer-symbol (c2d/load-image "resources/cancer.png"))
(def leo-symbol (c2d/load-image "resources/leo.png"))
(def virgo-symbol (c2d/load-image "resources/virgo.png"))
(def libra-symbol (c2d/load-image "resources/libra.png"))
(def scorpio-symbol (c2d/load-image "resources/scorpio.png"))
(def sag-symbol (c2d/load-image "resources/sagittarius.png"))
(def cap-symbol (c2d/load-image "resources/capricorn.png"))
(def aquarius-symbol (c2d/load-image "resources/aquarius.png"))
(def pisces-symbol (c2d/load-image "resources/pisces.png"))

(def zodiac-symbols [aries-symbol taurus-symbol gemini-symbol cancer-symbol leo-symbol virgo-symbol libra-symbol scorpio-symbol sag-symbol cap-symbol aquarius-symbol pisces-symbol])
(def zodiac-symbol-degrees (range 15 360 30))
(def zodiac-symbols-map (zipmap zodiac-symbols zodiac-symbol-degrees))

(def symbol-size 20)
(def symbol-spacing 10)

(defn draw-lines
  "draw 360 degrees in 30 degree increments"
  [canvas center radius]
  (doseq [x (range 0 360 30)]
    (c2d/line canvas center center (trig/on-circle-at-x center radius x) (trig/on-circle-at-y center radius x))))

(defn draw-symbols
  "draw zodiac signs in chart areas"
  [canvas center radius]
  (doseq [symbol (keys zodiac-symbols-map)]
    (c2d/image canvas symbol (- (trig/on-circle-at-x center radius (get zodiac-symbols-map symbol)) symbol-spacing) (- (trig/on-circle-at-y center radius (get zodiac-symbols-map symbol)) symbol-spacing) symbol-size symbol-size)))

(defn draw-chart
  "draw chart with radial lines and zodiac signs in chart areas" 
  [canvas canvas-boundary]
  (let [center (/ canvas-boundary 2)
        outer-radius (* canvas-boundary 0.45)
        inner-radius (* canvas-boundary 0.38)
        middle-band (* canvas-boundary 0.415)]
    (-> canvas
      ;; draw outer circle boundary
      (c2d/set-color :white)
      (c2d/ellipse center center (* outer-radius 2) (* outer-radius 2))
      (c2d/set-color :black)
      (c2d/ellipse center center (- (* outer-radius 2) 2) (- (* outer-radius 2) 2))
      ;; draw inner circle boundary
      (c2d/set-color :white)
      (c2d/ellipse center center (* inner-radius 2) (* inner-radius 2))
      (c2d/set-color :black)
      (c2d/ellipse center center (- (* inner-radius 2) 2) (- (* inner-radius 2) 2))
      (c2d/set-color :white)
      (c2d/set-stroke 1)
    )    
    (draw-lines canvas center outer-radius)
    (draw-symbols canvas center middle-band)
  canvas))

(defn lon-to-sign
  "converts longitude to degrees in sign"
  [lon]
  (cond
    (< lon 30) (format "%s° Aries" lon)
    (< lon 60) (format "%s° Taurus" (- lon 30))
    (< lon 90) (format "%s° Gemini" (- lon 60))
    (< lon 120) (format "%s° Cancer" (- lon 90))
    (< lon 150) (format "%s° Leo" (- lon 120))
    (< lon 180) (format "%s° Virgo" (- lon 150))
    (< lon 210) (format "%s° Libra" (- lon 180))
    (< lon 240) (format "%s° Scorpio" (- lon 210))
    (< lon 270) (format "%s° Sagittarius" (- lon 240))
    (< lon 300) (format "%s° Capricorn" (- lon 270))
    (< lon 330) (format "%s° Aquarius" (- lon 300))
    (< lon 360) (format "%s° Pisces" (- lon 330))))
