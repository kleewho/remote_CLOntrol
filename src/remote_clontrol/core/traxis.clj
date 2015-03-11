(ns remote-clontrol.core.traxis
  (:require [cats.monad.exception :as exc]
            [clojure.tools.logging :as log])
  (:import (com.lgi.traxis TraxisServiceImpl)
           (com.lgi.traxis.model.identity RecordableIdentity)
           (com.lgi.traxis.model.resource RecordableResource)
           (com.lgi.artichoke.http.configuration FusedRequestConfig)
           (com.lgi.traxis TraxisException)))

(def traxis-clients {:PL (new TraxisServiceImpl
                              (FusedRequestConfig/defaultFusedConfig)
                              "http://api.lgi.com/sg/traxis-pl")})

(defn is-already-recorded? [e _ _ _]
  (let [message (clojure.string/lower-case (.getMessage e))]
    (.contains message "already recorded")))


(defn get-recording-id [traxis-client identity imi]
  (let [recordingId (.recordingId traxis-client identity imi)]
    (if (.isPresent recordingId)
      (.get recordingId))))

(defmulti handle-error is-already-recorded?)

(defmethod handle-error true [e traxis-client identity imi]
  (log/info "Booking is already recorded " identity imi)
  (let [recordingId (.recordingId traxis-client identity imi)]
    (if (.isPresent recordingId)
      (.get recordingId)
      (throw e))))

(defmethod handle-error :default [e _ identity imi]
  (log/error "Exception when sending booking to traxis " identity imi)
  (throw e))

(defn interpret-traxis-error [traxis-client identity imi]
  (fn [e]
    (handle-error e traxis-client identity imi)))

(defn send-traxis [{:keys [region customer crid imi]}]
  (let [identity (RecordableIdentity/customerId customer)
        resource (RecordableResource/eventByCridAndImi crid imi)
        traxis-client (region traxis-clients)]
    (exc/try-or-recover (.sendBookingRequest traxis-client identity resource)
                        (interpret-traxis-error traxis-client identity imi))))
