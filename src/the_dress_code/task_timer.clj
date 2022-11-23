(ns the-dress-code.task-timer
  (:gen-class))

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))

;; 08-09-2022

(defn now [] (new java.util.Date))
;; => #'the-dress-code.task-timer/now

(defn my-fn
  [vector task due-hours length]
  (let [x (conj vector 
                {:task task :due-in due-hours :duration length :started (now)})] 
    (map (fn [task-info] 
           (let [duration (:duration task-info)
                 due-in (:due-in task-info)
                 task (:task task-info)]
             (if (> duration due-in)
               (str "Ain't got time to " task "!")
               task-info))
           ) x)))
;; => #'the-dress-code.task-timer/my-fn

(my-fn [{:task "shower" :due-in 0.75 :duration 0.25 :started (now)}
          {:task "eat" :due-in 0.5 :duration 1 :started (now)}
          {:task "code" :due-in 12 :duration 8 :started (now)}] 
         "exercise" 12 0.5)
;; => ({:task "shower",
;;      :due-in 0.75,
;;      :duration 0.25,
;;      :started #inst "2022-11-23T02:29:46.429-00:00"}
;;     "Ain't got time to eat!"
;;     {:task "code",
;;      :due-in 12,
;;      :duration 8,
;;      :started #inst "2022-11-23T02:29:46.429-00:00"}
;;     {:task "exercise",
;;      :due-in 12,
;;      :duration 0.5,
;;      :started #inst "2022-11-23T02:29:46.429-00:00"})

;; 08-21-22

;; data design: what data will I need for each timer?
;; [{:name “string”, :mins int, :color “string”, :alert “string”, :volume int, :session “string” :active true or false, :report true or false}]

(defn my-task
  "Return a hash map of the data I need for each task-timer "
  [name mins color alert volume session active report]
  {:name name :mins mins :color color :alert alert :volume volume :session session :active active :report report})
;; => #'the-dress-code.task-timer/my-task

(my-task "study" 25 "green" "ring" 5 "25/5 Pomodoro" true true)
;; => {:name "study",
;;     :mins 25,
;;     :color "green",
;;     :alert "ring",
;;     :volume 5,
;;     :session "25/5 Pomodoro",
;;     :active true,
;;     :report true}



