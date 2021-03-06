//* Licensed Materials - Property of                                  *
//* IBM                                                               *
//* Miracle A/S                                                       *
//*                                                                   *
//* eu.abc4trust.pabce.1.34                                           *
//*                                                                   *
//* (C) Copyright IBM Corp. 2014. All Rights Reserved.                *
//* (C) Copyright Miracle A/S, Denmark. 2014. All Rights Reserved.    *
//* US Government Users Restricted Rights - Use, duplication or       *
//* disclosure restricted by GSA ADP Schedule Contract with IBM Corp. *
//*                                                                   *
//* This file is licensed under the Apache License, Version 2.0 (the  *
//* "License"); you may not use this file except in compliance with   *
//* the License. You may obtain a copy of the License at:             *
//*   http://www.apache.org/licenses/LICENSE-2.0                      *
//* Unless required by applicable law or agreed to in writing,        *
//* software distributed under the License is distributed on an       *
//* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY            *
//* KIND, either express or implied.  See the License for the         *
//* specific language governing permissions and limitations           *
//* under the License.                                                *
//*/**/****************************************************************

package eu.abc4trust.abce.external.inspector;

import java.net.URI;
import java.util.List;

import com.google.inject.Inject;

import eu.abc4trust.abce.internal.inspector.credentialManager.CredentialManager;
import eu.abc4trust.abce.internal.inspector.credentialManager.CredentialManagerException;
import eu.abc4trust.cryptoEngine.CryptoEngineException;
import eu.abc4trust.cryptoEngine.inspector.CryptoEngineInspector;
import eu.abc4trust.returnTypes.InspectorPublicAndSecretKey;
import eu.abc4trust.xml.Attribute;
import eu.abc4trust.xml.FriendlyDescription;
import eu.abc4trust.xml.InspectorPublicKey;
import eu.abc4trust.xml.PresentationToken;
import eu.abc4trust.xml.SystemParameters;

public class InspectorAbcEngineImpl implements InspectorAbcEngine {

  private final CryptoEngineInspector cryptoEngine;
  private final CredentialManager credentialManager;
  
  @Inject
  public InspectorAbcEngineImpl(CryptoEngineInspector cryptoEngine, CredentialManager credentialManager) {
    this.cryptoEngine = cryptoEngine;
    this.credentialManager = credentialManager;
  }

  @Override
  public InspectorPublicKey setupInspectorPublicKey(SystemParameters sp, URI mechanism, URI uid,
      List<FriendlyDescription> friendlyInspectorDescription) throws CryptoEngineException, CredentialManagerException {
    InspectorPublicAndSecretKey keyPair = cryptoEngine.setupInspectorPublicKey(sp, mechanism, uid, friendlyInspectorDescription);
    credentialManager.storeInspectorSecretKey(uid, keyPair.secretKey);
    return keyPair.publicKey;
  }

  @Override
  public List<Attribute> inspect(PresentationToken t) throws CryptoEngineException {
    return cryptoEngine.inspect(t);
  }


}
