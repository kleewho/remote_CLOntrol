(ns remote-clontrol.core.traxis
  (:import (com.lgi.traxis TraxisServiceImpl)
           (com.lgi.traxis.model.identity RecordableIdentity)
           (com.lgi.traxis.model.resource RecordableResource)
           (com.lgi.artichoke.http.configuration FusedRequestConfig)
           (com.lgi.traxis TraxisException)))

(def traxis-clients :private {:PL (new TraxisServiceImpl
                                       (FusedRequestConfig/defaultFusedConfig)
                                       "http://api.lgi.com/sg/traxis-pl")})

(defn handle [e]
  e)

(defn send [{:keys [region customer crid imi]}]
  (let [identity (RecordableIdentity/customerId customer)
        resource (RecordableResource/eventByCridAndImi crid imi)
        traxis-client (region traxis-clients)]
    (try
      (.sendBookingRequest traxis-client identity resource))
    (catch TraxisException e
      (handle e))))
