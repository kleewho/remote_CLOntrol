(ns remote-booking.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [metrics.ring.expose :refer [expose-metrics-as-json]]
            [metrics.ring.instrument :refer [instrument]]
            [remote-booking.core.booking :as booking]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]))

(defroutes app-routes

  (PUT "/:region/:user/booking" [region user :as request]
       (json/write-str (str (booking/send-booking (-> request :params :stb-type))
                            (-> request :params :stb-type))))
  (route/not-found "Not Found"))

(defn init []
  (log/info "
Simple service for making bookings in LGI.

The authors wish you happy hacking. Wax on, wax off
")

  (def app
    (->
     (routes app-routes)
     (wrap-defaults api-defaults)
     (expose-metrics-as-json)
     (instrument))))
