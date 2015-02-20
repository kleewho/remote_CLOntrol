(ns remote-clontrol.core.dawn
;;  (:require [remote-clontrol.core.booking :refer [send-booking]])
  )

(defmethod send-booking :dawn [_ a]
  (str "dawn" a))
