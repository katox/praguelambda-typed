(defproject praguelambda-typed "0.1.0-SNAPSHOT"
  :descriptiona "Prague Lambda core.typed Intro"
  :url "http://www.meetup.com/Lambda-Meetup-Group/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/core.typed "0.2.19"]]
  :plugins [[lein-typed "0.3.1"]]
  :core.typed {:check [praguelambda-typed.core]})
