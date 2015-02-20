(ns remote-clontrol.core.booking)

(defn stb-type [type _] type)

(defmulti send-booking stb-type)

(defmethod send-booking :d4a [_]
  "dvr")

(defmethod send-booking :hzn [_]
  "hzn")
