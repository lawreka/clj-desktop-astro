(defproject clj-desktop-astro "0.1.0-SNAPSHOT"
  :description "Desktop astrology application with Clojure"
  :url "kathrynisabelle.com"
  :license {:name "Swiss Ephemeris"
            :url "https://www.astro.com/swisseph/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [clojure2d "1.4.3"]
                 [generateme/fastmath "2.1.6"]
                 [ephemeris "0.0.1"]]
  :main ^:skip-aot clj-desktop-astro.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
