package com.northqstandalone.maven.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.northqstandalone.maven.models.QMotionModel;
import com.northqstandalone.maven.services.QMotionService;
import com.northqstandalone.maven.view.View;

public class QMotionController {

    private QMotionModel model;
    private View view;
    private QMotionService service;

    public QMotionController(View view, QMotionModel model) {
        this.model = model;
        this.view = view;
        this.service = new QMotionService();
        this.view.addQPlugListener(new addQPlugListener(service, model));
    }

    // Action listener to receive event from view
    class addQPlugListener implements ActionListener {

        private QMotionService service;
        private QMotionModel model;

        public addQPlugListener(QMotionService service, QMotionModel model) {
            this.service = service;
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

}
