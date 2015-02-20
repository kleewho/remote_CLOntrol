(ns remote-clontrol.core.booking
  (:require [clojure.tools.logging :as log]
            [remote-clontrol.core.traxis :as traxis]
            [remote-clontrol.core.mds :as mds]))

(defn stb-type [type _]
  (log/info (str "keyword " type))
  type)

(defmulti send-booking stb-type)

(defmethod send-booking :d4a [_ a]
  (mds/send-d4a a))

(defmethod send-booking :hzn [_ a]
  (mds/send-hzn a))

(defmethod send-booking :dawn [_ a]
  (traxis/send a))
