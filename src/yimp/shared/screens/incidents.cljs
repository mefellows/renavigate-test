(ns yimp.shared.screens.incidents
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [yimp.shared.ui :as ui]
            [yimp.shared.components.incident-list :refer [incident-list-view]]
            [yimp.shared.styles :refer [styles]]))

(defn incidents []
   (fn [nav]
      (let [incidents (rf/subscribe [:incidents])
            user (rf/subscribe [:user])
            this (r/current-component)
            props (r/props this)
            loading (rf/subscribe [:sync])]
            [ui/view {:flex 1
                      :flex-direction "column"}
              [ui/header nav "Incidents"]
              [ui/view {:flex 9}
                [incident-list-view nav @incidents @loading]]
              (let [component (ui/floating-action-button (fn []
                                (rf/dispatch-sync [:create-new-incident @user])
                                (-> nav (.navigate "Edit"))))]
               [component
                 [ui/text {:style {:font-size 24
                                     :font-weight "400"
                                     :color "#FFF"}}
                                     "+"]])])))
