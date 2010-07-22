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

import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.gephi.desktop.streaming//Streaming//EN",
autostore = false)
public final class StreamingTopComponent extends TopComponent implements ExplorerManager.Provider {

    private static StreamingTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "org/gephi/desktop/streaming/resources/media-stream.png";
    private static final String PREFERRED_ID = "StreamingTopComponent";

    private StreamingController controller;
    private Children clientMasterChildren;
    private StreamingTreeView tree;

    public StreamingTopComponent() {

        tree = new StreamingTreeView();

        initComponents();
        setName(NbBundle.getMessage(StreamingTopComponent.class, "CTL_StreamingTopComponent"));
        setToolTipText(NbBundle.getMessage(StreamingTopComponent.class, "HINT_StreamingTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

        controller = Lookup.getDefault().lookup(StreamingController.class);
        controller.setTopComponent(this);

        clientMasterChildren = new Children.Array();

        associateLookup (ExplorerUtils.createLookup(mgr, getActionMap()));
        AbstractNode topnode = new AbstractNode(clientMasterChildren) {
            @Override
            public Action[] getActions(boolean context) {
                return new Action[0];
            }
        };
        mgr.setRootContext(topnode);
    }

    private class StreamingTreeView extends BeanTreeView {

        public StreamingTreeView() {
            super();
            setRootVisible(false);
        }
    }

    public synchronized void refreshModel(StreamingModel model) {
        Node clientNode = model.getClientNode();
        Node masterNode = model.getMasterNode();

        clientMasterChildren.remove(clientMasterChildren.getNodes());
        clientMasterChildren.add(new Node[]{clientNode, masterNode});
        tree.expandNode(clientNode);
        tree.expandNode(masterNode);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        topPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        javax.swing.JScrollPane treeView = tree;

        setLayout(new java.awt.GridBagLayout());

        topPanel.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(addButton, org.openide.util.NbBundle.getMessage(StreamingTopComponent.class, "StreamingTopComponent.addButton.text")); // NOI18N
        addButton.setToolTipText(org.openide.util.NbBundle.getMessage(StreamingTopComponent.class, "StreamingTopComponent.addButton.toolTipText")); // NOI18N
        addButton.setMaximumSize(new java.awt.Dimension(29, 29));
        addButton.setMinimumSize(new java.awt.Dimension(29, 29));
        addButton.setPreferredSize(new java.awt.Dimension(29, 29));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        topPanel.add(addButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(removeButton, org.openide.util.NbBundle.getMessage(StreamingTopComponent.class, "StreamingTopComponent.removeButton.text")); // NOI18N
        removeButton.setToolTipText(org.openide.util.NbBundle.getMessage(StreamingTopComponent.class, "StreamingTopComponent.removeButton.toolTipText")); // NOI18N
        removeButton.setEnabled(false);
        removeButton.setMaximumSize(new java.awt.Dimension(29, 29));
        removeButton.setMinimumSize(new java.awt.Dimension(29, 29));
        removeButton.setPreferredSize(new java.awt.Dimension(29, 29));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        topPanel.add(removeButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(settingsButton, org.openide.util.NbBundle.getMessage(StreamingTopComponent.class, "StreamingTopComponent.settingsButton.text")); // NOI18N
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        topPanel.add(settingsButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        topPanel.add(separator, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        add(topPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(treeView, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                controller.setSettings();
            }
        });

    }//GEN-LAST:event_settingsActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                controller.connectToStream();
            }
        });
    }//GEN-LAST:event_addActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JSeparator separator;
    private javax.swing.JButton settingsButton;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized StreamingTopComponent getDefault() {
        if (instance == null) {
            instance = new StreamingTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the StreamingTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized StreamingTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(StreamingTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof StreamingTopComponent) {
            return (StreamingTopComponent) win;
        }
        Logger.getLogger(StreamingTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    private final ExplorerManager mgr = new ExplorerManager();
    public ExplorerManager getExplorerManager() {
        return mgr;
    }

}
