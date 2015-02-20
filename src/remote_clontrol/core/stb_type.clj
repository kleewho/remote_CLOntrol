(ns remote-clontrol.core.stb-type)

(defn wrap-stb-type
  [handler]
  (fn [request]
    (if-let [stb (-> request :params :stb)]
      (handler (conj request {:stb (keyword stb)}))
      (handler request))))
