(ns remote-booking.core.dawn
;;  (:require [remote-booking.core.booking :refer [send-booking]])
  )

(defmethod send-booking :dawn [_ a]
  (str "dawn" a))
