(ns clj-desktop-astro.core
  (:require [clojure2d.core :as c2d]
            [clj-desktop-astro.trig :as trig]
            [clj-desktop-astro.chart :as chart]
            [clj-desktop-astro.planets :as planets]))

(def canvas-width 800)
(def canvas-height 800)
(def canvas-boundary (min canvas-width canvas-height))

(def my-canvas (c2d/black-canvas canvas-width canvas-height))

(def window (c2d/show-window my-canvas "✨"))

(defn -main
  "Launch application, draw chart and planets at current time"
  ;; TODO: schedule re-calculation and re-drawing of planets hourly
  []
  (c2d/with-canvas-> my-canvas
    (chart/draw-chart canvas-boundary)
    (planets/draw-planets canvas-boundary canvas-width)))
