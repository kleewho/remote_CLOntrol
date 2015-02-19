(defproject remote-booking "0.1.0-SNAPSHOT"
  :description "microservice for making bookings"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.3"]
                 [metrics-clojure "2.4.0"]
                 [metrics-clojure-graphite "2.4.0"]
                 [metrics-clojure-ring "2.4.0"]
                 [clj-http "1.0.1"]
                 [clj-time "0.9.0"]
                 [environ "1.0.0"]
                 [org.clojure/data.json "0.2.5"]
                 [org.clojure/algo.monads "0.1.5"]
                 [org.clojure/tools.logging "0.3.1"]
                 [ch.qos.logback/logback-classic "1.1.1"]
                 [net.logstash.logback/logstash-logback-encoder "3.6"]]
  :plugins [[lein-ring "0.9.1"]]
  :ring {:handler remote-booking.core.handler/app
         :init remote-booking.core.handler/init}
  :lis-opts
  {:pid-dir "/var/local/run"
   :install-dir "/var/local/clogrid"
   :init-script-install-dir "/etc/init.d"
   :jar-install-dir "/var/local/clogrid"
   :redirect-output-to "/var/local/clogrid/log"
   :jar-args ["-p" "8080"]
   :jvm-opts ["-server"
              "-Xms256M"
              "-Xmx512M"
              "-XX:MaxPermSize=128M"]}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
