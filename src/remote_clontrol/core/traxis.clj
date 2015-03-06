(ns remote-clontrol.core.traxis
  (:import (com.lgi.traxis TraxisServiceImpl)
           (com.lgi.artichoke.http.configuration FusedRequestConfig)))

(def traxis-clients {:PL (new TraxisServiceImpl
                              (FusedRequestConfig/defaultFusedConfig)
                              "http://api.lgi.com/sg/traxis-pl")})

(defn send [{:keys [region user crid imi]}]
  (str "sending to traxis data " region user crid imi))
