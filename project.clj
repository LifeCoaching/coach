(defproject coach "0.1.0-SNAPSHOT"
  :description "Personal trainer and work logbook"
  :url "http://example.com/FIXME"
  :license {:name "None"
            :url "None"}

  :source-paths ["src/clj" "src/cljs" "target/classes"]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2755" :scope "provided"]
                 [ring "1.3.1"]
                 [compojure "1.2.0"]
                 [enlive "1.1.5"]
                 [org.omcljs/om "0.8.7"]
                 [figwheel "0.1.4-SNAPSHOT"]
                 [environ "1.0.0"]
                 [com.cemerick/piggieback "0.1.5"]
                 [weasel "0.5.0"]
                 [leiningen "2.5.0"]]

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-environ "1.0.0"]]

  :min-lein-version "2.5.0"

  :uberjar-name "coach.jar"

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to     "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/out"
                                        :source-map    "resources/public/js/out.js.map"
                                        :preamble      ["react/react.min.js"]
                                        :externs       ["react/externs/react.js"]
                                        :optimizations :none
                                        :cache-analysis true
                                        :pretty-print  true}}}}

  :profiles {:dev {:repl-options {:init-ns coach.server
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :plugins [[lein-figwheel "0.1.4-SNAPSHOT"]]

                   :figwheel {:http-server-root "public"
                              :port 3449
                              :css-dirs ["resources/public/css"]}

                   :env {:is-dev true}

                   :cljsbuild {:builds {:app {:source-paths ["env/dev/cljs"]}}}}

             :uberjar {:hooks [leiningen.cljsbuild]
                       :env {:production true}
                       :omit-source true
                       :aot :all
                       :cljsbuild {:builds {:app
                                            {:source-paths ["env/prod/cljs"]
                                             :compiler
                                             {:optimizations :advanced
                                              :pretty-print false}}}}}})
