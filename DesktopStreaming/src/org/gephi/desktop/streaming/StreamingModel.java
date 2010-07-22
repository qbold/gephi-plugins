/*
Copyright 2008 WebAtlas
Authors : Mathieu Bastian, Mathieu Jacomy, Julian Bilcke
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.desktop.streaming;

import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import org.gephi.streaming.api.Report;

import org.gephi.streaming.api.StreamingConnection;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;

/**
 * @author panisson
 *
 */
public class StreamingModel {

    private Image clientImage = ImageUtilities.loadImage("org/gephi/desktop/streaming/resources/gephiclient.png", true);
    private Image masterImage = ImageUtilities.loadImage("org/gephi/desktop/streaming/resources/gephimaster.png", true);
    
    private boolean serverRunning;
    private String serverContext;
    
    private Children clientChildren = new Children.Array();
    private Children masterChildren = new Children.Array();

    private Node masterNode;
    private Node clientNode;
    
    public StreamingModel() {

        clientNode = new ClientNode();
        masterNode = new MasterNode();

        masterChildren.add(new Node[]{new StreamingServerNode()});
    }

    public void addConnection(StreamingConnection connection, Report report) {
        StreamingConnectionNode node = new StreamingConnectionNode(connection, report);
        clientChildren.add(new Node[]{node});
        
    }

    public boolean isServerRunning() {
        return serverRunning;
    }

    public void setServerRunning(boolean serverRunning) {
        this.serverRunning = serverRunning;
    }

    /**
     * @return the serverContext
     */
    public String getServerContext() {
        return serverContext;
    }

    /**
     * @param serverContext the serverContext to set
     */
    public void setServerContext(String serverContext) {
        this.serverContext = serverContext;
    }

    public Node getClientNode() {
        return clientNode;
    }

    public Node getMasterNode() {
        return masterNode;
    }

    private class ClientNode extends AbstractNode {

        private final Action addConnectionAction;

        public ClientNode() {
            super(clientChildren);
            setDisplayName("Client");

            addConnectionAction = new AbstractAction("Connect to Stream") {

                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            StreamingController controller = Lookup.getDefault().lookup(StreamingController.class);
                            controller.connectToStream();
                        }
                    });
                }
            };
        }
        @Override
        public Action[] getActions(boolean popup) {
            return new Action[]{addConnectionAction};
        }
        @Override
        public Image getIcon(int type) {
            return clientImage;
        }

        @Override
        public Image getOpenedIcon(int i) {
            return getIcon(i);
        }
        @Override
        public String getHtmlDisplayName() {
            return "<b>Client<b>";
        }
    }

    private class MasterNode extends AbstractNode {

        public MasterNode() {
            super(masterChildren);
            setDisplayName("Master");
        }
        @Override
        public Action[] getActions(boolean popup) {
            return new Action[]{};
        }
        @Override
        public Image getIcon(int type) {
            return masterImage;
        }
        @Override
        public Image getOpenedIcon(int i) {
            return getIcon(i);
        }
        @Override
        public String getHtmlDisplayName() {
            return "<b>Master<b>";
        }
    }
}
