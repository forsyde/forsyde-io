package io.github.forsyde.io.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import io.github.forsyde.io.services.MetaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMetaParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'int'", "'integer'", "'float'", "'bool'", "'boolean'", "'string'", "'str'", "'inout'", "'outin'", "'out'", "'outgoing'", "'in'", "'incoming'", "'vertex'", "'('", "')'", "'{'", "'}'", "'refines'", "','", "'property'", "':'", "'='", "'array'", "'<'", "'>'", "'object'", "'port'", "'edge'", "'constraint'", "'or'", "'double'", "'true'", "'multiple'", "'ordered'", "'target'", "'source'"
    };
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalMetaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMetaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMetaParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMeta.g"; }


    	private MetaGrammarAccess grammarAccess;

    	public void setGrammarAccess(MetaGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleModel"
    // InternalMeta.g:53:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalMeta.g:54:1: ( ruleModel EOF )
            // InternalMeta.g:55:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalMeta.g:62:1: ruleModel : ( ( rule__Model__TraitsAssignment )* ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:66:2: ( ( ( rule__Model__TraitsAssignment )* ) )
            // InternalMeta.g:67:2: ( ( rule__Model__TraitsAssignment )* )
            {
            // InternalMeta.g:67:2: ( ( rule__Model__TraitsAssignment )* )
            // InternalMeta.g:68:3: ( rule__Model__TraitsAssignment )*
            {
             before(grammarAccess.getModelAccess().getTraitsAssignment()); 
            // InternalMeta.g:69:3: ( rule__Model__TraitsAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==24||LA1_0==39) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMeta.g:69:4: rule__Model__TraitsAssignment
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Model__TraitsAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getTraitsAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleVertexTrait"
    // InternalMeta.g:78:1: entryRuleVertexTrait : ruleVertexTrait EOF ;
    public final void entryRuleVertexTrait() throws RecognitionException {
        try {
            // InternalMeta.g:79:1: ( ruleVertexTrait EOF )
            // InternalMeta.g:80:1: ruleVertexTrait EOF
            {
             before(grammarAccess.getVertexTraitRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTrait();

            state._fsp--;

             after(grammarAccess.getVertexTraitRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTrait"


    // $ANTLR start "ruleVertexTrait"
    // InternalMeta.g:87:1: ruleVertexTrait : ( ( rule__VertexTrait__Group__0 ) ) ;
    public final void ruleVertexTrait() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:91:2: ( ( ( rule__VertexTrait__Group__0 ) ) )
            // InternalMeta.g:92:2: ( ( rule__VertexTrait__Group__0 ) )
            {
            // InternalMeta.g:92:2: ( ( rule__VertexTrait__Group__0 ) )
            // InternalMeta.g:93:3: ( rule__VertexTrait__Group__0 )
            {
             before(grammarAccess.getVertexTraitAccess().getGroup()); 
            // InternalMeta.g:94:3: ( rule__VertexTrait__Group__0 )
            // InternalMeta.g:94:4: rule__VertexTrait__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTrait"


    // $ANTLR start "entryRuleVertexTraitProperty"
    // InternalMeta.g:103:1: entryRuleVertexTraitProperty : ruleVertexTraitProperty EOF ;
    public final void entryRuleVertexTraitProperty() throws RecognitionException {
        try {
            // InternalMeta.g:104:1: ( ruleVertexTraitProperty EOF )
            // InternalMeta.g:105:1: ruleVertexTraitProperty EOF
            {
             before(grammarAccess.getVertexTraitPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTraitProperty();

            state._fsp--;

             after(grammarAccess.getVertexTraitPropertyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTraitProperty"


    // $ANTLR start "ruleVertexTraitProperty"
    // InternalMeta.g:112:1: ruleVertexTraitProperty : ( ( rule__VertexTraitProperty__Group__0 ) ) ;
    public final void ruleVertexTraitProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:116:2: ( ( ( rule__VertexTraitProperty__Group__0 ) ) )
            // InternalMeta.g:117:2: ( ( rule__VertexTraitProperty__Group__0 ) )
            {
            // InternalMeta.g:117:2: ( ( rule__VertexTraitProperty__Group__0 ) )
            // InternalMeta.g:118:3: ( rule__VertexTraitProperty__Group__0 )
            {
             before(grammarAccess.getVertexTraitPropertyAccess().getGroup()); 
            // InternalMeta.g:119:3: ( rule__VertexTraitProperty__Group__0 )
            // InternalMeta.g:119:4: rule__VertexTraitProperty__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitProperty__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitPropertyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitProperty"


    // $ANTLR start "entryRuleVertexTraitPropertyType"
    // InternalMeta.g:128:1: entryRuleVertexTraitPropertyType : ruleVertexTraitPropertyType EOF ;
    public final void entryRuleVertexTraitPropertyType() throws RecognitionException {
        try {
            // InternalMeta.g:129:1: ( ruleVertexTraitPropertyType EOF )
            // InternalMeta.g:130:1: ruleVertexTraitPropertyType EOF
            {
             before(grammarAccess.getVertexTraitPropertyTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTraitPropertyType();

            state._fsp--;

             after(grammarAccess.getVertexTraitPropertyTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTraitPropertyType"


    // $ANTLR start "ruleVertexTraitPropertyType"
    // InternalMeta.g:137:1: ruleVertexTraitPropertyType : ( ( rule__VertexTraitPropertyType__Alternatives ) ) ;
    public final void ruleVertexTraitPropertyType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:141:2: ( ( ( rule__VertexTraitPropertyType__Alternatives ) ) )
            // InternalMeta.g:142:2: ( ( rule__VertexTraitPropertyType__Alternatives ) )
            {
            // InternalMeta.g:142:2: ( ( rule__VertexTraitPropertyType__Alternatives ) )
            // InternalMeta.g:143:3: ( rule__VertexTraitPropertyType__Alternatives )
            {
             before(grammarAccess.getVertexTraitPropertyTypeAccess().getAlternatives()); 
            // InternalMeta.g:144:3: ( rule__VertexTraitPropertyType__Alternatives )
            // InternalMeta.g:144:4: rule__VertexTraitPropertyType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitPropertyType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitPropertyTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitPropertyType"


    // $ANTLR start "entryRuleVertexTraitIntProperty"
    // InternalMeta.g:153:1: entryRuleVertexTraitIntProperty : ruleVertexTraitIntProperty EOF ;
    public final void entryRuleVertexTraitIntProperty() throws RecognitionException {
        try {
            // InternalMeta.g:154:1: ( ruleVertexTraitIntProperty EOF )
            // InternalMeta.g:155:1: ruleVertexTraitIntProperty EOF
            {
             before(grammarAccess.getVertexTraitIntPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTraitIntProperty();

            state._fsp--;

             after(grammarAccess.getVertexTraitIntPropertyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTraitIntProperty"


    // $ANTLR start "ruleVertexTraitIntProperty"
    // InternalMeta.g:162:1: ruleVertexTraitIntProperty : ( ( rule__VertexTraitIntProperty__Group__0 ) ) ;
    public final void ruleVertexTraitIntProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:166:2: ( ( ( rule__VertexTraitIntProperty__Group__0 ) ) )
            // InternalMeta.g:167:2: ( ( rule__VertexTraitIntProperty__Group__0 ) )
            {
            // InternalMeta.g:167:2: ( ( rule__VertexTraitIntProperty__Group__0 ) )
            // InternalMeta.g:168:3: ( rule__VertexTraitIntProperty__Group__0 )
            {
             before(grammarAccess.getVertexTraitIntPropertyAccess().getGroup()); 
            // InternalMeta.g:169:3: ( rule__VertexTraitIntProperty__Group__0 )
            // InternalMeta.g:169:4: rule__VertexTraitIntProperty__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitIntProperty__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitIntPropertyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitIntProperty"


    // $ANTLR start "entryRuleVertexTraitRealProperty"
    // InternalMeta.g:178:1: entryRuleVertexTraitRealProperty : ruleVertexTraitRealProperty EOF ;
    public final void entryRuleVertexTraitRealProperty() throws RecognitionException {
        try {
            // InternalMeta.g:179:1: ( ruleVertexTraitRealProperty EOF )
            // InternalMeta.g:180:1: ruleVertexTraitRealProperty EOF
            {
             before(grammarAccess.getVertexTraitRealPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTraitRealProperty();

            state._fsp--;

             after(grammarAccess.getVertexTraitRealPropertyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTraitRealProperty"


    // $ANTLR start "ruleVertexTraitRealProperty"
    // InternalMeta.g:187:1: ruleVertexTraitRealProperty : ( ( rule__VertexTraitRealProperty__Group__0 ) ) ;
    public final void ruleVertexTraitRealProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:191:2: ( ( ( rule__VertexTraitRealProperty__Group__0 ) ) )
            // InternalMeta.g:192:2: ( ( rule__VertexTraitRealProperty__Group__0 ) )
            {
            // InternalMeta.g:192:2: ( ( rule__VertexTraitRealProperty__Group__0 ) )
            // InternalMeta.g:193:3: ( rule__VertexTraitRealProperty__Group__0 )
            {
             before(grammarAccess.getVertexTraitRealPropertyAccess().getGroup()); 
            // InternalMeta.g:194:3: ( rule__VertexTraitRealProperty__Group__0 )
            // InternalMeta.g:194:4: rule__VertexTraitRealProperty__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitRealProperty__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitRealPropertyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitRealProperty"


    // $ANTLR start "entryRuleVertexTraitBoolProperty"
    // InternalMeta.g:203:1: entryRuleVertexTraitBoolProperty : ruleVertexTraitBoolProperty EOF ;
    public final void entryRuleVertexTraitBoolProperty() throws RecognitionException {
        try {
            // InternalMeta.g:204:1: ( ruleVertexTraitBoolProperty EOF )
            // InternalMeta.g:205:1: ruleVertexTraitBoolProperty EOF
            {
             before(grammarAccess.getVertexTraitBoolPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTraitBoolProperty();

            state._fsp--;

             after(grammarAccess.getVertexTraitBoolPropertyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTraitBoolProperty"


    // $ANTLR start "ruleVertexTraitBoolProperty"
    // InternalMeta.g:212:1: ruleVertexTraitBoolProperty : ( ( rule__VertexTraitBoolProperty__Group__0 ) ) ;
    public final void ruleVertexTraitBoolProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:216:2: ( ( ( rule__VertexTraitBoolProperty__Group__0 ) ) )
            // InternalMeta.g:217:2: ( ( rule__VertexTraitBoolProperty__Group__0 ) )
            {
            // InternalMeta.g:217:2: ( ( rule__VertexTraitBoolProperty__Group__0 ) )
            // InternalMeta.g:218:3: ( rule__VertexTraitBoolProperty__Group__0 )
            {
             before(grammarAccess.getVertexTraitBoolPropertyAccess().getGroup()); 
            // InternalMeta.g:219:3: ( rule__VertexTraitBoolProperty__Group__0 )
            // InternalMeta.g:219:4: rule__VertexTraitBoolProperty__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitBoolProperty__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitBoolPropertyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitBoolProperty"


    // $ANTLR start "entryRuleVertexTraitStringProperty"
    // InternalMeta.g:228:1: entryRuleVertexTraitStringProperty : ruleVertexTraitStringProperty EOF ;
    public final void entryRuleVertexTraitStringProperty() throws RecognitionException {
        try {
            // InternalMeta.g:229:1: ( ruleVertexTraitStringProperty EOF )
            // InternalMeta.g:230:1: ruleVertexTraitStringProperty EOF
            {
             before(grammarAccess.getVertexTraitStringPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTraitStringProperty();

            state._fsp--;

             after(grammarAccess.getVertexTraitStringPropertyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTraitStringProperty"


    // $ANTLR start "ruleVertexTraitStringProperty"
    // InternalMeta.g:237:1: ruleVertexTraitStringProperty : ( ( rule__VertexTraitStringProperty__Group__0 ) ) ;
    public final void ruleVertexTraitStringProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:241:2: ( ( ( rule__VertexTraitStringProperty__Group__0 ) ) )
            // InternalMeta.g:242:2: ( ( rule__VertexTraitStringProperty__Group__0 ) )
            {
            // InternalMeta.g:242:2: ( ( rule__VertexTraitStringProperty__Group__0 ) )
            // InternalMeta.g:243:3: ( rule__VertexTraitStringProperty__Group__0 )
            {
             before(grammarAccess.getVertexTraitStringPropertyAccess().getGroup()); 
            // InternalMeta.g:244:3: ( rule__VertexTraitStringProperty__Group__0 )
            // InternalMeta.g:244:4: rule__VertexTraitStringProperty__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitStringProperty__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitStringPropertyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitStringProperty"


    // $ANTLR start "entryRuleVertexTraitArrayProperty"
    // InternalMeta.g:253:1: entryRuleVertexTraitArrayProperty : ruleVertexTraitArrayProperty EOF ;
    public final void entryRuleVertexTraitArrayProperty() throws RecognitionException {
        try {
            // InternalMeta.g:254:1: ( ruleVertexTraitArrayProperty EOF )
            // InternalMeta.g:255:1: ruleVertexTraitArrayProperty EOF
            {
             before(grammarAccess.getVertexTraitArrayPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTraitArrayProperty();

            state._fsp--;

             after(grammarAccess.getVertexTraitArrayPropertyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTraitArrayProperty"


    // $ANTLR start "ruleVertexTraitArrayProperty"
    // InternalMeta.g:262:1: ruleVertexTraitArrayProperty : ( ( rule__VertexTraitArrayProperty__Group__0 ) ) ;
    public final void ruleVertexTraitArrayProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:266:2: ( ( ( rule__VertexTraitArrayProperty__Group__0 ) ) )
            // InternalMeta.g:267:2: ( ( rule__VertexTraitArrayProperty__Group__0 ) )
            {
            // InternalMeta.g:267:2: ( ( rule__VertexTraitArrayProperty__Group__0 ) )
            // InternalMeta.g:268:3: ( rule__VertexTraitArrayProperty__Group__0 )
            {
             before(grammarAccess.getVertexTraitArrayPropertyAccess().getGroup()); 
            // InternalMeta.g:269:3: ( rule__VertexTraitArrayProperty__Group__0 )
            // InternalMeta.g:269:4: rule__VertexTraitArrayProperty__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitArrayProperty__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitArrayPropertyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitArrayProperty"


    // $ANTLR start "entryRuleVertexTraitObjectProperty"
    // InternalMeta.g:278:1: entryRuleVertexTraitObjectProperty : ruleVertexTraitObjectProperty EOF ;
    public final void entryRuleVertexTraitObjectProperty() throws RecognitionException {
        try {
            // InternalMeta.g:279:1: ( ruleVertexTraitObjectProperty EOF )
            // InternalMeta.g:280:1: ruleVertexTraitObjectProperty EOF
            {
             before(grammarAccess.getVertexTraitObjectPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTraitObjectProperty();

            state._fsp--;

             after(grammarAccess.getVertexTraitObjectPropertyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTraitObjectProperty"


    // $ANTLR start "ruleVertexTraitObjectProperty"
    // InternalMeta.g:287:1: ruleVertexTraitObjectProperty : ( ( rule__VertexTraitObjectProperty__Group__0 ) ) ;
    public final void ruleVertexTraitObjectProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:291:2: ( ( ( rule__VertexTraitObjectProperty__Group__0 ) ) )
            // InternalMeta.g:292:2: ( ( rule__VertexTraitObjectProperty__Group__0 ) )
            {
            // InternalMeta.g:292:2: ( ( rule__VertexTraitObjectProperty__Group__0 ) )
            // InternalMeta.g:293:3: ( rule__VertexTraitObjectProperty__Group__0 )
            {
             before(grammarAccess.getVertexTraitObjectPropertyAccess().getGroup()); 
            // InternalMeta.g:294:3: ( rule__VertexTraitObjectProperty__Group__0 )
            // InternalMeta.g:294:4: rule__VertexTraitObjectProperty__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitObjectProperty__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitObjectPropertyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitObjectProperty"


    // $ANTLR start "entryRuleVertexTraitPort"
    // InternalMeta.g:303:1: entryRuleVertexTraitPort : ruleVertexTraitPort EOF ;
    public final void entryRuleVertexTraitPort() throws RecognitionException {
        try {
            // InternalMeta.g:304:1: ( ruleVertexTraitPort EOF )
            // InternalMeta.g:305:1: ruleVertexTraitPort EOF
            {
             before(grammarAccess.getVertexTraitPortRule()); 
            pushFollow(FOLLOW_1);
            ruleVertexTraitPort();

            state._fsp--;

             after(grammarAccess.getVertexTraitPortRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertexTraitPort"


    // $ANTLR start "ruleVertexTraitPort"
    // InternalMeta.g:312:1: ruleVertexTraitPort : ( ( rule__VertexTraitPort__UnorderedGroup ) ) ;
    public final void ruleVertexTraitPort() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:316:2: ( ( ( rule__VertexTraitPort__UnorderedGroup ) ) )
            // InternalMeta.g:317:2: ( ( rule__VertexTraitPort__UnorderedGroup ) )
            {
            // InternalMeta.g:317:2: ( ( rule__VertexTraitPort__UnorderedGroup ) )
            // InternalMeta.g:318:3: ( rule__VertexTraitPort__UnorderedGroup )
            {
             before(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()); 
            // InternalMeta.g:319:3: ( rule__VertexTraitPort__UnorderedGroup )
            // InternalMeta.g:319:4: rule__VertexTraitPort__UnorderedGroup
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__UnorderedGroup();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitPort"


    // $ANTLR start "entryRuleEdgeTrait"
    // InternalMeta.g:328:1: entryRuleEdgeTrait : ruleEdgeTrait EOF ;
    public final void entryRuleEdgeTrait() throws RecognitionException {
        try {
            // InternalMeta.g:329:1: ( ruleEdgeTrait EOF )
            // InternalMeta.g:330:1: ruleEdgeTrait EOF
            {
             before(grammarAccess.getEdgeTraitRule()); 
            pushFollow(FOLLOW_1);
            ruleEdgeTrait();

            state._fsp--;

             after(grammarAccess.getEdgeTraitRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEdgeTrait"


    // $ANTLR start "ruleEdgeTrait"
    // InternalMeta.g:337:1: ruleEdgeTrait : ( ( rule__EdgeTrait__Group__0 ) ) ;
    public final void ruleEdgeTrait() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:341:2: ( ( ( rule__EdgeTrait__Group__0 ) ) )
            // InternalMeta.g:342:2: ( ( rule__EdgeTrait__Group__0 ) )
            {
            // InternalMeta.g:342:2: ( ( rule__EdgeTrait__Group__0 ) )
            // InternalMeta.g:343:3: ( rule__EdgeTrait__Group__0 )
            {
             before(grammarAccess.getEdgeTraitAccess().getGroup()); 
            // InternalMeta.g:344:3: ( rule__EdgeTrait__Group__0 )
            // InternalMeta.g:344:4: rule__EdgeTrait__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEdgeTraitAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEdgeTrait"


    // $ANTLR start "entryRuleEdgeTraitConstraint"
    // InternalMeta.g:353:1: entryRuleEdgeTraitConstraint : ruleEdgeTraitConstraint EOF ;
    public final void entryRuleEdgeTraitConstraint() throws RecognitionException {
        try {
            // InternalMeta.g:354:1: ( ruleEdgeTraitConstraint EOF )
            // InternalMeta.g:355:1: ruleEdgeTraitConstraint EOF
            {
             before(grammarAccess.getEdgeTraitConstraintRule()); 
            pushFollow(FOLLOW_1);
            ruleEdgeTraitConstraint();

            state._fsp--;

             after(grammarAccess.getEdgeTraitConstraintRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEdgeTraitConstraint"


    // $ANTLR start "ruleEdgeTraitConstraint"
    // InternalMeta.g:362:1: ruleEdgeTraitConstraint : ( ( rule__EdgeTraitConstraint__Group__0 ) ) ;
    public final void ruleEdgeTraitConstraint() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:366:2: ( ( ( rule__EdgeTraitConstraint__Group__0 ) ) )
            // InternalMeta.g:367:2: ( ( rule__EdgeTraitConstraint__Group__0 ) )
            {
            // InternalMeta.g:367:2: ( ( rule__EdgeTraitConstraint__Group__0 ) )
            // InternalMeta.g:368:3: ( rule__EdgeTraitConstraint__Group__0 )
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getGroup()); 
            // InternalMeta.g:369:3: ( rule__EdgeTraitConstraint__Group__0 )
            // InternalMeta.g:369:4: rule__EdgeTraitConstraint__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEdgeTraitConstraintAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEdgeTraitConstraint"


    // $ANTLR start "ruleVertexTraitPortDirection"
    // InternalMeta.g:378:1: ruleVertexTraitPortDirection : ( ( rule__VertexTraitPortDirection__Alternatives ) ) ;
    public final void ruleVertexTraitPortDirection() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:382:1: ( ( ( rule__VertexTraitPortDirection__Alternatives ) ) )
            // InternalMeta.g:383:2: ( ( rule__VertexTraitPortDirection__Alternatives ) )
            {
            // InternalMeta.g:383:2: ( ( rule__VertexTraitPortDirection__Alternatives ) )
            // InternalMeta.g:384:3: ( rule__VertexTraitPortDirection__Alternatives )
            {
             before(grammarAccess.getVertexTraitPortDirectionAccess().getAlternatives()); 
            // InternalMeta.g:385:3: ( rule__VertexTraitPortDirection__Alternatives )
            // InternalMeta.g:385:4: rule__VertexTraitPortDirection__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitPortDirection__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitPortDirectionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertexTraitPortDirection"


    // $ANTLR start "rule__Model__TraitsAlternatives_0"
    // InternalMeta.g:393:1: rule__Model__TraitsAlternatives_0 : ( ( ruleVertexTrait ) | ( ruleEdgeTrait ) );
    public final void rule__Model__TraitsAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:397:1: ( ( ruleVertexTrait ) | ( ruleEdgeTrait ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==24) ) {
                alt2=1;
            }
            else if ( (LA2_0==39) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalMeta.g:398:2: ( ruleVertexTrait )
                    {
                    // InternalMeta.g:398:2: ( ruleVertexTrait )
                    // InternalMeta.g:399:3: ruleVertexTrait
                    {
                     before(grammarAccess.getModelAccess().getTraitsVertexTraitParserRuleCall_0_0()); 
                    pushFollow(FOLLOW_2);
                    ruleVertexTrait();

                    state._fsp--;

                     after(grammarAccess.getModelAccess().getTraitsVertexTraitParserRuleCall_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:404:2: ( ruleEdgeTrait )
                    {
                    // InternalMeta.g:404:2: ( ruleEdgeTrait )
                    // InternalMeta.g:405:3: ruleEdgeTrait
                    {
                     before(grammarAccess.getModelAccess().getTraitsEdgeTraitParserRuleCall_0_1()); 
                    pushFollow(FOLLOW_2);
                    ruleEdgeTrait();

                    state._fsp--;

                     after(grammarAccess.getModelAccess().getTraitsEdgeTraitParserRuleCall_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__TraitsAlternatives_0"


    // $ANTLR start "rule__VertexTraitPropertyType__Alternatives"
    // InternalMeta.g:414:1: rule__VertexTraitPropertyType__Alternatives : ( ( ruleVertexTraitIntProperty ) | ( ruleVertexTraitRealProperty ) | ( ruleVertexTraitBoolProperty ) | ( ruleVertexTraitStringProperty ) | ( ruleVertexTraitArrayProperty ) | ( ruleVertexTraitObjectProperty ) );
    public final void rule__VertexTraitPropertyType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:418:1: ( ( ruleVertexTraitIntProperty ) | ( ruleVertexTraitRealProperty ) | ( ruleVertexTraitBoolProperty ) | ( ruleVertexTraitStringProperty ) | ( ruleVertexTraitArrayProperty ) | ( ruleVertexTraitObjectProperty ) )
            int alt3=6;
            switch ( input.LA(1) ) {
            case 11:
            case 12:
                {
                alt3=1;
                }
                break;
            case 13:
            case 42:
                {
                alt3=2;
                }
                break;
            case 14:
            case 15:
                {
                alt3=3;
                }
                break;
            case 16:
            case 17:
                {
                alt3=4;
                }
                break;
            case 34:
                {
                alt3=5;
                }
                break;
            case 37:
                {
                alt3=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalMeta.g:419:2: ( ruleVertexTraitIntProperty )
                    {
                    // InternalMeta.g:419:2: ( ruleVertexTraitIntProperty )
                    // InternalMeta.g:420:3: ruleVertexTraitIntProperty
                    {
                     before(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitIntPropertyParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleVertexTraitIntProperty();

                    state._fsp--;

                     after(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitIntPropertyParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:425:2: ( ruleVertexTraitRealProperty )
                    {
                    // InternalMeta.g:425:2: ( ruleVertexTraitRealProperty )
                    // InternalMeta.g:426:3: ruleVertexTraitRealProperty
                    {
                     before(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitRealPropertyParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleVertexTraitRealProperty();

                    state._fsp--;

                     after(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitRealPropertyParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMeta.g:431:2: ( ruleVertexTraitBoolProperty )
                    {
                    // InternalMeta.g:431:2: ( ruleVertexTraitBoolProperty )
                    // InternalMeta.g:432:3: ruleVertexTraitBoolProperty
                    {
                     before(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitBoolPropertyParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleVertexTraitBoolProperty();

                    state._fsp--;

                     after(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitBoolPropertyParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalMeta.g:437:2: ( ruleVertexTraitStringProperty )
                    {
                    // InternalMeta.g:437:2: ( ruleVertexTraitStringProperty )
                    // InternalMeta.g:438:3: ruleVertexTraitStringProperty
                    {
                     before(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitStringPropertyParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
                    ruleVertexTraitStringProperty();

                    state._fsp--;

                     after(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitStringPropertyParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalMeta.g:443:2: ( ruleVertexTraitArrayProperty )
                    {
                    // InternalMeta.g:443:2: ( ruleVertexTraitArrayProperty )
                    // InternalMeta.g:444:3: ruleVertexTraitArrayProperty
                    {
                     before(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitArrayPropertyParserRuleCall_4()); 
                    pushFollow(FOLLOW_2);
                    ruleVertexTraitArrayProperty();

                    state._fsp--;

                     after(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitArrayPropertyParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalMeta.g:449:2: ( ruleVertexTraitObjectProperty )
                    {
                    // InternalMeta.g:449:2: ( ruleVertexTraitObjectProperty )
                    // InternalMeta.g:450:3: ruleVertexTraitObjectProperty
                    {
                     before(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitObjectPropertyParserRuleCall_5()); 
                    pushFollow(FOLLOW_2);
                    ruleVertexTraitObjectProperty();

                    state._fsp--;

                     after(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitObjectPropertyParserRuleCall_5()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPropertyType__Alternatives"


    // $ANTLR start "rule__VertexTraitIntProperty__Alternatives_0"
    // InternalMeta.g:459:1: rule__VertexTraitIntProperty__Alternatives_0 : ( ( 'int' ) | ( 'integer' ) );
    public final void rule__VertexTraitIntProperty__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:463:1: ( ( 'int' ) | ( 'integer' ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==11) ) {
                alt4=1;
            }
            else if ( (LA4_0==12) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalMeta.g:464:2: ( 'int' )
                    {
                    // InternalMeta.g:464:2: ( 'int' )
                    // InternalMeta.g:465:3: 'int'
                    {
                     before(grammarAccess.getVertexTraitIntPropertyAccess().getIntKeyword_0_0()); 
                    match(input,11,FOLLOW_2); 
                     after(grammarAccess.getVertexTraitIntPropertyAccess().getIntKeyword_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:470:2: ( 'integer' )
                    {
                    // InternalMeta.g:470:2: ( 'integer' )
                    // InternalMeta.g:471:3: 'integer'
                    {
                     before(grammarAccess.getVertexTraitIntPropertyAccess().getIntegerKeyword_0_1()); 
                    match(input,12,FOLLOW_2); 
                     after(grammarAccess.getVertexTraitIntPropertyAccess().getIntegerKeyword_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__Alternatives_0"


    // $ANTLR start "rule__VertexTraitRealProperty__Alternatives_0"
    // InternalMeta.g:480:1: rule__VertexTraitRealProperty__Alternatives_0 : ( ( 'float' ) | ( ( rule__VertexTraitRealProperty__DoubleAssignment_0_1 ) ) );
    public final void rule__VertexTraitRealProperty__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:484:1: ( ( 'float' ) | ( ( rule__VertexTraitRealProperty__DoubleAssignment_0_1 ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==13) ) {
                alt5=1;
            }
            else if ( (LA5_0==42) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalMeta.g:485:2: ( 'float' )
                    {
                    // InternalMeta.g:485:2: ( 'float' )
                    // InternalMeta.g:486:3: 'float'
                    {
                     before(grammarAccess.getVertexTraitRealPropertyAccess().getFloatKeyword_0_0()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getVertexTraitRealPropertyAccess().getFloatKeyword_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:491:2: ( ( rule__VertexTraitRealProperty__DoubleAssignment_0_1 ) )
                    {
                    // InternalMeta.g:491:2: ( ( rule__VertexTraitRealProperty__DoubleAssignment_0_1 ) )
                    // InternalMeta.g:492:3: ( rule__VertexTraitRealProperty__DoubleAssignment_0_1 )
                    {
                     before(grammarAccess.getVertexTraitRealPropertyAccess().getDoubleAssignment_0_1()); 
                    // InternalMeta.g:493:3: ( rule__VertexTraitRealProperty__DoubleAssignment_0_1 )
                    // InternalMeta.g:493:4: rule__VertexTraitRealProperty__DoubleAssignment_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitRealProperty__DoubleAssignment_0_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getVertexTraitRealPropertyAccess().getDoubleAssignment_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__Alternatives_0"


    // $ANTLR start "rule__VertexTraitBoolProperty__Alternatives_0"
    // InternalMeta.g:501:1: rule__VertexTraitBoolProperty__Alternatives_0 : ( ( 'bool' ) | ( 'boolean' ) );
    public final void rule__VertexTraitBoolProperty__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:505:1: ( ( 'bool' ) | ( 'boolean' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==14) ) {
                alt6=1;
            }
            else if ( (LA6_0==15) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalMeta.g:506:2: ( 'bool' )
                    {
                    // InternalMeta.g:506:2: ( 'bool' )
                    // InternalMeta.g:507:3: 'bool'
                    {
                     before(grammarAccess.getVertexTraitBoolPropertyAccess().getBoolKeyword_0_0()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getVertexTraitBoolPropertyAccess().getBoolKeyword_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:512:2: ( 'boolean' )
                    {
                    // InternalMeta.g:512:2: ( 'boolean' )
                    // InternalMeta.g:513:3: 'boolean'
                    {
                     before(grammarAccess.getVertexTraitBoolPropertyAccess().getBooleanKeyword_0_1()); 
                    match(input,15,FOLLOW_2); 
                     after(grammarAccess.getVertexTraitBoolPropertyAccess().getBooleanKeyword_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__Alternatives_0"


    // $ANTLR start "rule__VertexTraitStringProperty__Alternatives_0"
    // InternalMeta.g:522:1: rule__VertexTraitStringProperty__Alternatives_0 : ( ( 'string' ) | ( 'str' ) );
    public final void rule__VertexTraitStringProperty__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:526:1: ( ( 'string' ) | ( 'str' ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==16) ) {
                alt7=1;
            }
            else if ( (LA7_0==17) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalMeta.g:527:2: ( 'string' )
                    {
                    // InternalMeta.g:527:2: ( 'string' )
                    // InternalMeta.g:528:3: 'string'
                    {
                     before(grammarAccess.getVertexTraitStringPropertyAccess().getStringKeyword_0_0()); 
                    match(input,16,FOLLOW_2); 
                     after(grammarAccess.getVertexTraitStringPropertyAccess().getStringKeyword_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:533:2: ( 'str' )
                    {
                    // InternalMeta.g:533:2: ( 'str' )
                    // InternalMeta.g:534:3: 'str'
                    {
                     before(grammarAccess.getVertexTraitStringPropertyAccess().getStrKeyword_0_1()); 
                    match(input,17,FOLLOW_2); 
                     after(grammarAccess.getVertexTraitStringPropertyAccess().getStrKeyword_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__Alternatives_0"


    // $ANTLR start "rule__EdgeTraitConstraint__Alternatives_1"
    // InternalMeta.g:543:1: rule__EdgeTraitConstraint__Alternatives_1 : ( ( ( rule__EdgeTraitConstraint__TargetAssignment_1_0 ) ) | ( ( rule__EdgeTraitConstraint__SourceAssignment_1_1 ) ) );
    public final void rule__EdgeTraitConstraint__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:547:1: ( ( ( rule__EdgeTraitConstraint__TargetAssignment_1_0 ) ) | ( ( rule__EdgeTraitConstraint__SourceAssignment_1_1 ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==46) ) {
                alt8=1;
            }
            else if ( (LA8_0==47) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalMeta.g:548:2: ( ( rule__EdgeTraitConstraint__TargetAssignment_1_0 ) )
                    {
                    // InternalMeta.g:548:2: ( ( rule__EdgeTraitConstraint__TargetAssignment_1_0 ) )
                    // InternalMeta.g:549:3: ( rule__EdgeTraitConstraint__TargetAssignment_1_0 )
                    {
                     before(grammarAccess.getEdgeTraitConstraintAccess().getTargetAssignment_1_0()); 
                    // InternalMeta.g:550:3: ( rule__EdgeTraitConstraint__TargetAssignment_1_0 )
                    // InternalMeta.g:550:4: rule__EdgeTraitConstraint__TargetAssignment_1_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__EdgeTraitConstraint__TargetAssignment_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getEdgeTraitConstraintAccess().getTargetAssignment_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:554:2: ( ( rule__EdgeTraitConstraint__SourceAssignment_1_1 ) )
                    {
                    // InternalMeta.g:554:2: ( ( rule__EdgeTraitConstraint__SourceAssignment_1_1 ) )
                    // InternalMeta.g:555:3: ( rule__EdgeTraitConstraint__SourceAssignment_1_1 )
                    {
                     before(grammarAccess.getEdgeTraitConstraintAccess().getSourceAssignment_1_1()); 
                    // InternalMeta.g:556:3: ( rule__EdgeTraitConstraint__SourceAssignment_1_1 )
                    // InternalMeta.g:556:4: rule__EdgeTraitConstraint__SourceAssignment_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__EdgeTraitConstraint__SourceAssignment_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getEdgeTraitConstraintAccess().getSourceAssignment_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Alternatives_1"


    // $ANTLR start "rule__VertexTraitPortDirection__Alternatives"
    // InternalMeta.g:564:1: rule__VertexTraitPortDirection__Alternatives : ( ( ( 'inout' ) ) | ( ( 'outin' ) ) | ( ( 'out' ) ) | ( ( 'outgoing' ) ) | ( ( 'in' ) ) | ( ( 'incoming' ) ) );
    public final void rule__VertexTraitPortDirection__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:568:1: ( ( ( 'inout' ) ) | ( ( 'outin' ) ) | ( ( 'out' ) ) | ( ( 'outgoing' ) ) | ( ( 'in' ) ) | ( ( 'incoming' ) ) )
            int alt9=6;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt9=1;
                }
                break;
            case 19:
                {
                alt9=2;
                }
                break;
            case 20:
                {
                alt9=3;
                }
                break;
            case 21:
                {
                alt9=4;
                }
                break;
            case 22:
                {
                alt9=5;
                }
                break;
            case 23:
                {
                alt9=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalMeta.g:569:2: ( ( 'inout' ) )
                    {
                    // InternalMeta.g:569:2: ( ( 'inout' ) )
                    // InternalMeta.g:570:3: ( 'inout' )
                    {
                     before(grammarAccess.getVertexTraitPortDirectionAccess().getBIDIRECTIONALEnumLiteralDeclaration_0()); 
                    // InternalMeta.g:571:3: ( 'inout' )
                    // InternalMeta.g:571:4: 'inout'
                    {
                    match(input,18,FOLLOW_2); 

                    }

                     after(grammarAccess.getVertexTraitPortDirectionAccess().getBIDIRECTIONALEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:575:2: ( ( 'outin' ) )
                    {
                    // InternalMeta.g:575:2: ( ( 'outin' ) )
                    // InternalMeta.g:576:3: ( 'outin' )
                    {
                     before(grammarAccess.getVertexTraitPortDirectionAccess().getBIDIRECTIONALEnumLiteralDeclaration_1()); 
                    // InternalMeta.g:577:3: ( 'outin' )
                    // InternalMeta.g:577:4: 'outin'
                    {
                    match(input,19,FOLLOW_2); 

                    }

                     after(grammarAccess.getVertexTraitPortDirectionAccess().getBIDIRECTIONALEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMeta.g:581:2: ( ( 'out' ) )
                    {
                    // InternalMeta.g:581:2: ( ( 'out' ) )
                    // InternalMeta.g:582:3: ( 'out' )
                    {
                     before(grammarAccess.getVertexTraitPortDirectionAccess().getOUTGOINGEnumLiteralDeclaration_2()); 
                    // InternalMeta.g:583:3: ( 'out' )
                    // InternalMeta.g:583:4: 'out'
                    {
                    match(input,20,FOLLOW_2); 

                    }

                     after(grammarAccess.getVertexTraitPortDirectionAccess().getOUTGOINGEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalMeta.g:587:2: ( ( 'outgoing' ) )
                    {
                    // InternalMeta.g:587:2: ( ( 'outgoing' ) )
                    // InternalMeta.g:588:3: ( 'outgoing' )
                    {
                     before(grammarAccess.getVertexTraitPortDirectionAccess().getOUTGOINGEnumLiteralDeclaration_3()); 
                    // InternalMeta.g:589:3: ( 'outgoing' )
                    // InternalMeta.g:589:4: 'outgoing'
                    {
                    match(input,21,FOLLOW_2); 

                    }

                     after(grammarAccess.getVertexTraitPortDirectionAccess().getOUTGOINGEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalMeta.g:593:2: ( ( 'in' ) )
                    {
                    // InternalMeta.g:593:2: ( ( 'in' ) )
                    // InternalMeta.g:594:3: ( 'in' )
                    {
                     before(grammarAccess.getVertexTraitPortDirectionAccess().getINCOMINGEnumLiteralDeclaration_4()); 
                    // InternalMeta.g:595:3: ( 'in' )
                    // InternalMeta.g:595:4: 'in'
                    {
                    match(input,22,FOLLOW_2); 

                    }

                     after(grammarAccess.getVertexTraitPortDirectionAccess().getINCOMINGEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalMeta.g:599:2: ( ( 'incoming' ) )
                    {
                    // InternalMeta.g:599:2: ( ( 'incoming' ) )
                    // InternalMeta.g:600:3: ( 'incoming' )
                    {
                     before(grammarAccess.getVertexTraitPortDirectionAccess().getINCOMINGEnumLiteralDeclaration_5()); 
                    // InternalMeta.g:601:3: ( 'incoming' )
                    // InternalMeta.g:601:4: 'incoming'
                    {
                    match(input,23,FOLLOW_2); 

                    }

                     after(grammarAccess.getVertexTraitPortDirectionAccess().getINCOMINGEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPortDirection__Alternatives"


    // $ANTLR start "rule__VertexTrait__Group__0"
    // InternalMeta.g:609:1: rule__VertexTrait__Group__0 : rule__VertexTrait__Group__0__Impl rule__VertexTrait__Group__1 ;
    public final void rule__VertexTrait__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:613:1: ( rule__VertexTrait__Group__0__Impl rule__VertexTrait__Group__1 )
            // InternalMeta.g:614:2: rule__VertexTrait__Group__0__Impl rule__VertexTrait__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__VertexTrait__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__0"


    // $ANTLR start "rule__VertexTrait__Group__0__Impl"
    // InternalMeta.g:621:1: rule__VertexTrait__Group__0__Impl : ( 'vertex' ) ;
    public final void rule__VertexTrait__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:625:1: ( ( 'vertex' ) )
            // InternalMeta.g:626:1: ( 'vertex' )
            {
            // InternalMeta.g:626:1: ( 'vertex' )
            // InternalMeta.g:627:2: 'vertex'
            {
             before(grammarAccess.getVertexTraitAccess().getVertexKeyword_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getVertexKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__0__Impl"


    // $ANTLR start "rule__VertexTrait__Group__1"
    // InternalMeta.g:636:1: rule__VertexTrait__Group__1 : rule__VertexTrait__Group__1__Impl rule__VertexTrait__Group__2 ;
    public final void rule__VertexTrait__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:640:1: ( rule__VertexTrait__Group__1__Impl rule__VertexTrait__Group__2 )
            // InternalMeta.g:641:2: rule__VertexTrait__Group__1__Impl rule__VertexTrait__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__VertexTrait__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__1"


    // $ANTLR start "rule__VertexTrait__Group__1__Impl"
    // InternalMeta.g:648:1: rule__VertexTrait__Group__1__Impl : ( ( rule__VertexTrait__NameAssignment_1 ) ) ;
    public final void rule__VertexTrait__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:652:1: ( ( ( rule__VertexTrait__NameAssignment_1 ) ) )
            // InternalMeta.g:653:1: ( ( rule__VertexTrait__NameAssignment_1 ) )
            {
            // InternalMeta.g:653:1: ( ( rule__VertexTrait__NameAssignment_1 ) )
            // InternalMeta.g:654:2: ( rule__VertexTrait__NameAssignment_1 )
            {
             before(grammarAccess.getVertexTraitAccess().getNameAssignment_1()); 
            // InternalMeta.g:655:2: ( rule__VertexTrait__NameAssignment_1 )
            // InternalMeta.g:655:3: rule__VertexTrait__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__VertexTrait__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__1__Impl"


    // $ANTLR start "rule__VertexTrait__Group__2"
    // InternalMeta.g:663:1: rule__VertexTrait__Group__2 : rule__VertexTrait__Group__2__Impl rule__VertexTrait__Group__3 ;
    public final void rule__VertexTrait__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:667:1: ( rule__VertexTrait__Group__2__Impl rule__VertexTrait__Group__3 )
            // InternalMeta.g:668:2: rule__VertexTrait__Group__2__Impl rule__VertexTrait__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__VertexTrait__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__2"


    // $ANTLR start "rule__VertexTrait__Group__2__Impl"
    // InternalMeta.g:675:1: rule__VertexTrait__Group__2__Impl : ( ( rule__VertexTrait__Group_2__0 )? ) ;
    public final void rule__VertexTrait__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:679:1: ( ( ( rule__VertexTrait__Group_2__0 )? ) )
            // InternalMeta.g:680:1: ( ( rule__VertexTrait__Group_2__0 )? )
            {
            // InternalMeta.g:680:1: ( ( rule__VertexTrait__Group_2__0 )? )
            // InternalMeta.g:681:2: ( rule__VertexTrait__Group_2__0 )?
            {
             before(grammarAccess.getVertexTraitAccess().getGroup_2()); 
            // InternalMeta.g:682:2: ( rule__VertexTrait__Group_2__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==29) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalMeta.g:682:3: rule__VertexTrait__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTrait__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVertexTraitAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__2__Impl"


    // $ANTLR start "rule__VertexTrait__Group__3"
    // InternalMeta.g:690:1: rule__VertexTrait__Group__3 : rule__VertexTrait__Group__3__Impl rule__VertexTrait__Group__4 ;
    public final void rule__VertexTrait__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:694:1: ( rule__VertexTrait__Group__3__Impl rule__VertexTrait__Group__4 )
            // InternalMeta.g:695:2: rule__VertexTrait__Group__3__Impl rule__VertexTrait__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__VertexTrait__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__3"


    // $ANTLR start "rule__VertexTrait__Group__3__Impl"
    // InternalMeta.g:702:1: rule__VertexTrait__Group__3__Impl : ( '(' ) ;
    public final void rule__VertexTrait__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:706:1: ( ( '(' ) )
            // InternalMeta.g:707:1: ( '(' )
            {
            // InternalMeta.g:707:1: ( '(' )
            // InternalMeta.g:708:2: '('
            {
             before(grammarAccess.getVertexTraitAccess().getLeftParenthesisKeyword_3()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getLeftParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__3__Impl"


    // $ANTLR start "rule__VertexTrait__Group__4"
    // InternalMeta.g:717:1: rule__VertexTrait__Group__4 : rule__VertexTrait__Group__4__Impl rule__VertexTrait__Group__5 ;
    public final void rule__VertexTrait__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:721:1: ( rule__VertexTrait__Group__4__Impl rule__VertexTrait__Group__5 )
            // InternalMeta.g:722:2: rule__VertexTrait__Group__4__Impl rule__VertexTrait__Group__5
            {
            pushFollow(FOLLOW_6);
            rule__VertexTrait__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__4"


    // $ANTLR start "rule__VertexTrait__Group__4__Impl"
    // InternalMeta.g:729:1: rule__VertexTrait__Group__4__Impl : ( ( rule__VertexTrait__PortsAssignment_4 )* ) ;
    public final void rule__VertexTrait__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:733:1: ( ( ( rule__VertexTrait__PortsAssignment_4 )* ) )
            // InternalMeta.g:734:1: ( ( rule__VertexTrait__PortsAssignment_4 )* )
            {
            // InternalMeta.g:734:1: ( ( rule__VertexTrait__PortsAssignment_4 )* )
            // InternalMeta.g:735:2: ( rule__VertexTrait__PortsAssignment_4 )*
            {
             before(grammarAccess.getVertexTraitAccess().getPortsAssignment_4()); 
            // InternalMeta.g:736:2: ( rule__VertexTrait__PortsAssignment_4 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=18 && LA11_0<=23)||LA11_0==38||(LA11_0>=44 && LA11_0<=45)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalMeta.g:736:3: rule__VertexTrait__PortsAssignment_4
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__VertexTrait__PortsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getVertexTraitAccess().getPortsAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__4__Impl"


    // $ANTLR start "rule__VertexTrait__Group__5"
    // InternalMeta.g:744:1: rule__VertexTrait__Group__5 : rule__VertexTrait__Group__5__Impl rule__VertexTrait__Group__6 ;
    public final void rule__VertexTrait__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:748:1: ( rule__VertexTrait__Group__5__Impl rule__VertexTrait__Group__6 )
            // InternalMeta.g:749:2: rule__VertexTrait__Group__5__Impl rule__VertexTrait__Group__6
            {
            pushFollow(FOLLOW_8);
            rule__VertexTrait__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__5"


    // $ANTLR start "rule__VertexTrait__Group__5__Impl"
    // InternalMeta.g:756:1: rule__VertexTrait__Group__5__Impl : ( ')' ) ;
    public final void rule__VertexTrait__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:760:1: ( ( ')' ) )
            // InternalMeta.g:761:1: ( ')' )
            {
            // InternalMeta.g:761:1: ( ')' )
            // InternalMeta.g:762:2: ')'
            {
             before(grammarAccess.getVertexTraitAccess().getRightParenthesisKeyword_5()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getRightParenthesisKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__5__Impl"


    // $ANTLR start "rule__VertexTrait__Group__6"
    // InternalMeta.g:771:1: rule__VertexTrait__Group__6 : rule__VertexTrait__Group__6__Impl rule__VertexTrait__Group__7 ;
    public final void rule__VertexTrait__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:775:1: ( rule__VertexTrait__Group__6__Impl rule__VertexTrait__Group__7 )
            // InternalMeta.g:776:2: rule__VertexTrait__Group__6__Impl rule__VertexTrait__Group__7
            {
            pushFollow(FOLLOW_9);
            rule__VertexTrait__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__6"


    // $ANTLR start "rule__VertexTrait__Group__6__Impl"
    // InternalMeta.g:783:1: rule__VertexTrait__Group__6__Impl : ( '{' ) ;
    public final void rule__VertexTrait__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:787:1: ( ( '{' ) )
            // InternalMeta.g:788:1: ( '{' )
            {
            // InternalMeta.g:788:1: ( '{' )
            // InternalMeta.g:789:2: '{'
            {
             before(grammarAccess.getVertexTraitAccess().getLeftCurlyBracketKeyword_6()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getLeftCurlyBracketKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__6__Impl"


    // $ANTLR start "rule__VertexTrait__Group__7"
    // InternalMeta.g:798:1: rule__VertexTrait__Group__7 : rule__VertexTrait__Group__7__Impl rule__VertexTrait__Group__8 ;
    public final void rule__VertexTrait__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:802:1: ( rule__VertexTrait__Group__7__Impl rule__VertexTrait__Group__8 )
            // InternalMeta.g:803:2: rule__VertexTrait__Group__7__Impl rule__VertexTrait__Group__8
            {
            pushFollow(FOLLOW_9);
            rule__VertexTrait__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__7"


    // $ANTLR start "rule__VertexTrait__Group__7__Impl"
    // InternalMeta.g:810:1: rule__VertexTrait__Group__7__Impl : ( ( rule__VertexTrait__PropertiesAssignment_7 )* ) ;
    public final void rule__VertexTrait__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:814:1: ( ( ( rule__VertexTrait__PropertiesAssignment_7 )* ) )
            // InternalMeta.g:815:1: ( ( rule__VertexTrait__PropertiesAssignment_7 )* )
            {
            // InternalMeta.g:815:1: ( ( rule__VertexTrait__PropertiesAssignment_7 )* )
            // InternalMeta.g:816:2: ( rule__VertexTrait__PropertiesAssignment_7 )*
            {
             before(grammarAccess.getVertexTraitAccess().getPropertiesAssignment_7()); 
            // InternalMeta.g:817:2: ( rule__VertexTrait__PropertiesAssignment_7 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==31) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalMeta.g:817:3: rule__VertexTrait__PropertiesAssignment_7
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__VertexTrait__PropertiesAssignment_7();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getVertexTraitAccess().getPropertiesAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__7__Impl"


    // $ANTLR start "rule__VertexTrait__Group__8"
    // InternalMeta.g:825:1: rule__VertexTrait__Group__8 : rule__VertexTrait__Group__8__Impl ;
    public final void rule__VertexTrait__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:829:1: ( rule__VertexTrait__Group__8__Impl )
            // InternalMeta.g:830:2: rule__VertexTrait__Group__8__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group__8__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__8"


    // $ANTLR start "rule__VertexTrait__Group__8__Impl"
    // InternalMeta.g:836:1: rule__VertexTrait__Group__8__Impl : ( '}' ) ;
    public final void rule__VertexTrait__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:840:1: ( ( '}' ) )
            // InternalMeta.g:841:1: ( '}' )
            {
            // InternalMeta.g:841:1: ( '}' )
            // InternalMeta.g:842:2: '}'
            {
             before(grammarAccess.getVertexTraitAccess().getRightCurlyBracketKeyword_8()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getRightCurlyBracketKeyword_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group__8__Impl"


    // $ANTLR start "rule__VertexTrait__Group_2__0"
    // InternalMeta.g:852:1: rule__VertexTrait__Group_2__0 : rule__VertexTrait__Group_2__0__Impl rule__VertexTrait__Group_2__1 ;
    public final void rule__VertexTrait__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:856:1: ( rule__VertexTrait__Group_2__0__Impl rule__VertexTrait__Group_2__1 )
            // InternalMeta.g:857:2: rule__VertexTrait__Group_2__0__Impl rule__VertexTrait__Group_2__1
            {
            pushFollow(FOLLOW_4);
            rule__VertexTrait__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2__0"


    // $ANTLR start "rule__VertexTrait__Group_2__0__Impl"
    // InternalMeta.g:864:1: rule__VertexTrait__Group_2__0__Impl : ( 'refines' ) ;
    public final void rule__VertexTrait__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:868:1: ( ( 'refines' ) )
            // InternalMeta.g:869:1: ( 'refines' )
            {
            // InternalMeta.g:869:1: ( 'refines' )
            // InternalMeta.g:870:2: 'refines'
            {
             before(grammarAccess.getVertexTraitAccess().getRefinesKeyword_2_0()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getRefinesKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2__0__Impl"


    // $ANTLR start "rule__VertexTrait__Group_2__1"
    // InternalMeta.g:879:1: rule__VertexTrait__Group_2__1 : rule__VertexTrait__Group_2__1__Impl rule__VertexTrait__Group_2__2 ;
    public final void rule__VertexTrait__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:883:1: ( rule__VertexTrait__Group_2__1__Impl rule__VertexTrait__Group_2__2 )
            // InternalMeta.g:884:2: rule__VertexTrait__Group_2__1__Impl rule__VertexTrait__Group_2__2
            {
            pushFollow(FOLLOW_11);
            rule__VertexTrait__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2__1"


    // $ANTLR start "rule__VertexTrait__Group_2__1__Impl"
    // InternalMeta.g:891:1: rule__VertexTrait__Group_2__1__Impl : ( ( rule__VertexTrait__RefinesAssignment_2_1 ) ) ;
    public final void rule__VertexTrait__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:895:1: ( ( ( rule__VertexTrait__RefinesAssignment_2_1 ) ) )
            // InternalMeta.g:896:1: ( ( rule__VertexTrait__RefinesAssignment_2_1 ) )
            {
            // InternalMeta.g:896:1: ( ( rule__VertexTrait__RefinesAssignment_2_1 ) )
            // InternalMeta.g:897:2: ( rule__VertexTrait__RefinesAssignment_2_1 )
            {
             before(grammarAccess.getVertexTraitAccess().getRefinesAssignment_2_1()); 
            // InternalMeta.g:898:2: ( rule__VertexTrait__RefinesAssignment_2_1 )
            // InternalMeta.g:898:3: rule__VertexTrait__RefinesAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__VertexTrait__RefinesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitAccess().getRefinesAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2__1__Impl"


    // $ANTLR start "rule__VertexTrait__Group_2__2"
    // InternalMeta.g:906:1: rule__VertexTrait__Group_2__2 : rule__VertexTrait__Group_2__2__Impl ;
    public final void rule__VertexTrait__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:910:1: ( rule__VertexTrait__Group_2__2__Impl )
            // InternalMeta.g:911:2: rule__VertexTrait__Group_2__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2__2"


    // $ANTLR start "rule__VertexTrait__Group_2__2__Impl"
    // InternalMeta.g:917:1: rule__VertexTrait__Group_2__2__Impl : ( ( rule__VertexTrait__Group_2_2__0 )* ) ;
    public final void rule__VertexTrait__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:921:1: ( ( ( rule__VertexTrait__Group_2_2__0 )* ) )
            // InternalMeta.g:922:1: ( ( rule__VertexTrait__Group_2_2__0 )* )
            {
            // InternalMeta.g:922:1: ( ( rule__VertexTrait__Group_2_2__0 )* )
            // InternalMeta.g:923:2: ( rule__VertexTrait__Group_2_2__0 )*
            {
             before(grammarAccess.getVertexTraitAccess().getGroup_2_2()); 
            // InternalMeta.g:924:2: ( rule__VertexTrait__Group_2_2__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==30) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalMeta.g:924:3: rule__VertexTrait__Group_2_2__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__VertexTrait__Group_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getVertexTraitAccess().getGroup_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2__2__Impl"


    // $ANTLR start "rule__VertexTrait__Group_2_2__0"
    // InternalMeta.g:933:1: rule__VertexTrait__Group_2_2__0 : rule__VertexTrait__Group_2_2__0__Impl rule__VertexTrait__Group_2_2__1 ;
    public final void rule__VertexTrait__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:937:1: ( rule__VertexTrait__Group_2_2__0__Impl rule__VertexTrait__Group_2_2__1 )
            // InternalMeta.g:938:2: rule__VertexTrait__Group_2_2__0__Impl rule__VertexTrait__Group_2_2__1
            {
            pushFollow(FOLLOW_4);
            rule__VertexTrait__Group_2_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group_2_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2_2__0"


    // $ANTLR start "rule__VertexTrait__Group_2_2__0__Impl"
    // InternalMeta.g:945:1: rule__VertexTrait__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__VertexTrait__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:949:1: ( ( ',' ) )
            // InternalMeta.g:950:1: ( ',' )
            {
            // InternalMeta.g:950:1: ( ',' )
            // InternalMeta.g:951:2: ','
            {
             before(grammarAccess.getVertexTraitAccess().getCommaKeyword_2_2_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getCommaKeyword_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2_2__0__Impl"


    // $ANTLR start "rule__VertexTrait__Group_2_2__1"
    // InternalMeta.g:960:1: rule__VertexTrait__Group_2_2__1 : rule__VertexTrait__Group_2_2__1__Impl ;
    public final void rule__VertexTrait__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:964:1: ( rule__VertexTrait__Group_2_2__1__Impl )
            // InternalMeta.g:965:2: rule__VertexTrait__Group_2_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTrait__Group_2_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2_2__1"


    // $ANTLR start "rule__VertexTrait__Group_2_2__1__Impl"
    // InternalMeta.g:971:1: rule__VertexTrait__Group_2_2__1__Impl : ( ( rule__VertexTrait__RefinesAssignment_2_2_1 ) ) ;
    public final void rule__VertexTrait__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:975:1: ( ( ( rule__VertexTrait__RefinesAssignment_2_2_1 ) ) )
            // InternalMeta.g:976:1: ( ( rule__VertexTrait__RefinesAssignment_2_2_1 ) )
            {
            // InternalMeta.g:976:1: ( ( rule__VertexTrait__RefinesAssignment_2_2_1 ) )
            // InternalMeta.g:977:2: ( rule__VertexTrait__RefinesAssignment_2_2_1 )
            {
             before(grammarAccess.getVertexTraitAccess().getRefinesAssignment_2_2_1()); 
            // InternalMeta.g:978:2: ( rule__VertexTrait__RefinesAssignment_2_2_1 )
            // InternalMeta.g:978:3: rule__VertexTrait__RefinesAssignment_2_2_1
            {
            pushFollow(FOLLOW_2);
            rule__VertexTrait__RefinesAssignment_2_2_1();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitAccess().getRefinesAssignment_2_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__Group_2_2__1__Impl"


    // $ANTLR start "rule__VertexTraitProperty__Group__0"
    // InternalMeta.g:987:1: rule__VertexTraitProperty__Group__0 : rule__VertexTraitProperty__Group__0__Impl rule__VertexTraitProperty__Group__1 ;
    public final void rule__VertexTraitProperty__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:991:1: ( rule__VertexTraitProperty__Group__0__Impl rule__VertexTraitProperty__Group__1 )
            // InternalMeta.g:992:2: rule__VertexTraitProperty__Group__0__Impl rule__VertexTraitProperty__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__VertexTraitProperty__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitProperty__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__Group__0"


    // $ANTLR start "rule__VertexTraitProperty__Group__0__Impl"
    // InternalMeta.g:999:1: rule__VertexTraitProperty__Group__0__Impl : ( 'property' ) ;
    public final void rule__VertexTraitProperty__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1003:1: ( ( 'property' ) )
            // InternalMeta.g:1004:1: ( 'property' )
            {
            // InternalMeta.g:1004:1: ( 'property' )
            // InternalMeta.g:1005:2: 'property'
            {
             before(grammarAccess.getVertexTraitPropertyAccess().getPropertyKeyword_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getVertexTraitPropertyAccess().getPropertyKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__Group__0__Impl"


    // $ANTLR start "rule__VertexTraitProperty__Group__1"
    // InternalMeta.g:1014:1: rule__VertexTraitProperty__Group__1 : rule__VertexTraitProperty__Group__1__Impl rule__VertexTraitProperty__Group__2 ;
    public final void rule__VertexTraitProperty__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1018:1: ( rule__VertexTraitProperty__Group__1__Impl rule__VertexTraitProperty__Group__2 )
            // InternalMeta.g:1019:2: rule__VertexTraitProperty__Group__1__Impl rule__VertexTraitProperty__Group__2
            {
            pushFollow(FOLLOW_13);
            rule__VertexTraitProperty__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitProperty__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__Group__1"


    // $ANTLR start "rule__VertexTraitProperty__Group__1__Impl"
    // InternalMeta.g:1026:1: rule__VertexTraitProperty__Group__1__Impl : ( ( rule__VertexTraitProperty__NameAssignment_1 ) ) ;
    public final void rule__VertexTraitProperty__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1030:1: ( ( ( rule__VertexTraitProperty__NameAssignment_1 ) ) )
            // InternalMeta.g:1031:1: ( ( rule__VertexTraitProperty__NameAssignment_1 ) )
            {
            // InternalMeta.g:1031:1: ( ( rule__VertexTraitProperty__NameAssignment_1 ) )
            // InternalMeta.g:1032:2: ( rule__VertexTraitProperty__NameAssignment_1 )
            {
             before(grammarAccess.getVertexTraitPropertyAccess().getNameAssignment_1()); 
            // InternalMeta.g:1033:2: ( rule__VertexTraitProperty__NameAssignment_1 )
            // InternalMeta.g:1033:3: rule__VertexTraitProperty__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitProperty__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitPropertyAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__Group__1__Impl"


    // $ANTLR start "rule__VertexTraitProperty__Group__2"
    // InternalMeta.g:1041:1: rule__VertexTraitProperty__Group__2 : rule__VertexTraitProperty__Group__2__Impl rule__VertexTraitProperty__Group__3 ;
    public final void rule__VertexTraitProperty__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1045:1: ( rule__VertexTraitProperty__Group__2__Impl rule__VertexTraitProperty__Group__3 )
            // InternalMeta.g:1046:2: rule__VertexTraitProperty__Group__2__Impl rule__VertexTraitProperty__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__VertexTraitProperty__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitProperty__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__Group__2"


    // $ANTLR start "rule__VertexTraitProperty__Group__2__Impl"
    // InternalMeta.g:1053:1: rule__VertexTraitProperty__Group__2__Impl : ( ':' ) ;
    public final void rule__VertexTraitProperty__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1057:1: ( ( ':' ) )
            // InternalMeta.g:1058:1: ( ':' )
            {
            // InternalMeta.g:1058:1: ( ':' )
            // InternalMeta.g:1059:2: ':'
            {
             before(grammarAccess.getVertexTraitPropertyAccess().getColonKeyword_2()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getVertexTraitPropertyAccess().getColonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__Group__2__Impl"


    // $ANTLR start "rule__VertexTraitProperty__Group__3"
    // InternalMeta.g:1068:1: rule__VertexTraitProperty__Group__3 : rule__VertexTraitProperty__Group__3__Impl ;
    public final void rule__VertexTraitProperty__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1072:1: ( rule__VertexTraitProperty__Group__3__Impl )
            // InternalMeta.g:1073:2: rule__VertexTraitProperty__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitProperty__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__Group__3"


    // $ANTLR start "rule__VertexTraitProperty__Group__3__Impl"
    // InternalMeta.g:1079:1: rule__VertexTraitProperty__Group__3__Impl : ( ( rule__VertexTraitProperty__TypeAssignment_3 ) ) ;
    public final void rule__VertexTraitProperty__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1083:1: ( ( ( rule__VertexTraitProperty__TypeAssignment_3 ) ) )
            // InternalMeta.g:1084:1: ( ( rule__VertexTraitProperty__TypeAssignment_3 ) )
            {
            // InternalMeta.g:1084:1: ( ( rule__VertexTraitProperty__TypeAssignment_3 ) )
            // InternalMeta.g:1085:2: ( rule__VertexTraitProperty__TypeAssignment_3 )
            {
             before(grammarAccess.getVertexTraitPropertyAccess().getTypeAssignment_3()); 
            // InternalMeta.g:1086:2: ( rule__VertexTraitProperty__TypeAssignment_3 )
            // InternalMeta.g:1086:3: rule__VertexTraitProperty__TypeAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitProperty__TypeAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitPropertyAccess().getTypeAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__Group__3__Impl"


    // $ANTLR start "rule__VertexTraitIntProperty__Group__0"
    // InternalMeta.g:1095:1: rule__VertexTraitIntProperty__Group__0 : rule__VertexTraitIntProperty__Group__0__Impl rule__VertexTraitIntProperty__Group__1 ;
    public final void rule__VertexTraitIntProperty__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1099:1: ( rule__VertexTraitIntProperty__Group__0__Impl rule__VertexTraitIntProperty__Group__1 )
            // InternalMeta.g:1100:2: rule__VertexTraitIntProperty__Group__0__Impl rule__VertexTraitIntProperty__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__VertexTraitIntProperty__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitIntProperty__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__Group__0"


    // $ANTLR start "rule__VertexTraitIntProperty__Group__0__Impl"
    // InternalMeta.g:1107:1: rule__VertexTraitIntProperty__Group__0__Impl : ( ( rule__VertexTraitIntProperty__Alternatives_0 ) ) ;
    public final void rule__VertexTraitIntProperty__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1111:1: ( ( ( rule__VertexTraitIntProperty__Alternatives_0 ) ) )
            // InternalMeta.g:1112:1: ( ( rule__VertexTraitIntProperty__Alternatives_0 ) )
            {
            // InternalMeta.g:1112:1: ( ( rule__VertexTraitIntProperty__Alternatives_0 ) )
            // InternalMeta.g:1113:2: ( rule__VertexTraitIntProperty__Alternatives_0 )
            {
             before(grammarAccess.getVertexTraitIntPropertyAccess().getAlternatives_0()); 
            // InternalMeta.g:1114:2: ( rule__VertexTraitIntProperty__Alternatives_0 )
            // InternalMeta.g:1114:3: rule__VertexTraitIntProperty__Alternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitIntProperty__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitIntPropertyAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__Group__0__Impl"


    // $ANTLR start "rule__VertexTraitIntProperty__Group__1"
    // InternalMeta.g:1122:1: rule__VertexTraitIntProperty__Group__1 : rule__VertexTraitIntProperty__Group__1__Impl ;
    public final void rule__VertexTraitIntProperty__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1126:1: ( rule__VertexTraitIntProperty__Group__1__Impl )
            // InternalMeta.g:1127:2: rule__VertexTraitIntProperty__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitIntProperty__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__Group__1"


    // $ANTLR start "rule__VertexTraitIntProperty__Group__1__Impl"
    // InternalMeta.g:1133:1: rule__VertexTraitIntProperty__Group__1__Impl : ( ( rule__VertexTraitIntProperty__Group_1__0 )? ) ;
    public final void rule__VertexTraitIntProperty__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1137:1: ( ( ( rule__VertexTraitIntProperty__Group_1__0 )? ) )
            // InternalMeta.g:1138:1: ( ( rule__VertexTraitIntProperty__Group_1__0 )? )
            {
            // InternalMeta.g:1138:1: ( ( rule__VertexTraitIntProperty__Group_1__0 )? )
            // InternalMeta.g:1139:2: ( rule__VertexTraitIntProperty__Group_1__0 )?
            {
             before(grammarAccess.getVertexTraitIntPropertyAccess().getGroup_1()); 
            // InternalMeta.g:1140:2: ( rule__VertexTraitIntProperty__Group_1__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==33) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalMeta.g:1140:3: rule__VertexTraitIntProperty__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitIntProperty__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVertexTraitIntPropertyAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__Group__1__Impl"


    // $ANTLR start "rule__VertexTraitIntProperty__Group_1__0"
    // InternalMeta.g:1149:1: rule__VertexTraitIntProperty__Group_1__0 : rule__VertexTraitIntProperty__Group_1__0__Impl rule__VertexTraitIntProperty__Group_1__1 ;
    public final void rule__VertexTraitIntProperty__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1153:1: ( rule__VertexTraitIntProperty__Group_1__0__Impl rule__VertexTraitIntProperty__Group_1__1 )
            // InternalMeta.g:1154:2: rule__VertexTraitIntProperty__Group_1__0__Impl rule__VertexTraitIntProperty__Group_1__1
            {
            pushFollow(FOLLOW_16);
            rule__VertexTraitIntProperty__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitIntProperty__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__Group_1__0"


    // $ANTLR start "rule__VertexTraitIntProperty__Group_1__0__Impl"
    // InternalMeta.g:1161:1: rule__VertexTraitIntProperty__Group_1__0__Impl : ( '=' ) ;
    public final void rule__VertexTraitIntProperty__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1165:1: ( ( '=' ) )
            // InternalMeta.g:1166:1: ( '=' )
            {
            // InternalMeta.g:1166:1: ( '=' )
            // InternalMeta.g:1167:2: '='
            {
             before(grammarAccess.getVertexTraitIntPropertyAccess().getEqualsSignKeyword_1_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getVertexTraitIntPropertyAccess().getEqualsSignKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__Group_1__0__Impl"


    // $ANTLR start "rule__VertexTraitIntProperty__Group_1__1"
    // InternalMeta.g:1176:1: rule__VertexTraitIntProperty__Group_1__1 : rule__VertexTraitIntProperty__Group_1__1__Impl ;
    public final void rule__VertexTraitIntProperty__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1180:1: ( rule__VertexTraitIntProperty__Group_1__1__Impl )
            // InternalMeta.g:1181:2: rule__VertexTraitIntProperty__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitIntProperty__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__Group_1__1"


    // $ANTLR start "rule__VertexTraitIntProperty__Group_1__1__Impl"
    // InternalMeta.g:1187:1: rule__VertexTraitIntProperty__Group_1__1__Impl : ( ( rule__VertexTraitIntProperty__DefaultAssignment_1_1 ) ) ;
    public final void rule__VertexTraitIntProperty__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1191:1: ( ( ( rule__VertexTraitIntProperty__DefaultAssignment_1_1 ) ) )
            // InternalMeta.g:1192:1: ( ( rule__VertexTraitIntProperty__DefaultAssignment_1_1 ) )
            {
            // InternalMeta.g:1192:1: ( ( rule__VertexTraitIntProperty__DefaultAssignment_1_1 ) )
            // InternalMeta.g:1193:2: ( rule__VertexTraitIntProperty__DefaultAssignment_1_1 )
            {
             before(grammarAccess.getVertexTraitIntPropertyAccess().getDefaultAssignment_1_1()); 
            // InternalMeta.g:1194:2: ( rule__VertexTraitIntProperty__DefaultAssignment_1_1 )
            // InternalMeta.g:1194:3: rule__VertexTraitIntProperty__DefaultAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitIntProperty__DefaultAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitIntPropertyAccess().getDefaultAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__Group_1__1__Impl"


    // $ANTLR start "rule__VertexTraitRealProperty__Group__0"
    // InternalMeta.g:1203:1: rule__VertexTraitRealProperty__Group__0 : rule__VertexTraitRealProperty__Group__0__Impl rule__VertexTraitRealProperty__Group__1 ;
    public final void rule__VertexTraitRealProperty__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1207:1: ( rule__VertexTraitRealProperty__Group__0__Impl rule__VertexTraitRealProperty__Group__1 )
            // InternalMeta.g:1208:2: rule__VertexTraitRealProperty__Group__0__Impl rule__VertexTraitRealProperty__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__VertexTraitRealProperty__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitRealProperty__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__Group__0"


    // $ANTLR start "rule__VertexTraitRealProperty__Group__0__Impl"
    // InternalMeta.g:1215:1: rule__VertexTraitRealProperty__Group__0__Impl : ( ( rule__VertexTraitRealProperty__Alternatives_0 ) ) ;
    public final void rule__VertexTraitRealProperty__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1219:1: ( ( ( rule__VertexTraitRealProperty__Alternatives_0 ) ) )
            // InternalMeta.g:1220:1: ( ( rule__VertexTraitRealProperty__Alternatives_0 ) )
            {
            // InternalMeta.g:1220:1: ( ( rule__VertexTraitRealProperty__Alternatives_0 ) )
            // InternalMeta.g:1221:2: ( rule__VertexTraitRealProperty__Alternatives_0 )
            {
             before(grammarAccess.getVertexTraitRealPropertyAccess().getAlternatives_0()); 
            // InternalMeta.g:1222:2: ( rule__VertexTraitRealProperty__Alternatives_0 )
            // InternalMeta.g:1222:3: rule__VertexTraitRealProperty__Alternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitRealProperty__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitRealPropertyAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__Group__0__Impl"


    // $ANTLR start "rule__VertexTraitRealProperty__Group__1"
    // InternalMeta.g:1230:1: rule__VertexTraitRealProperty__Group__1 : rule__VertexTraitRealProperty__Group__1__Impl ;
    public final void rule__VertexTraitRealProperty__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1234:1: ( rule__VertexTraitRealProperty__Group__1__Impl )
            // InternalMeta.g:1235:2: rule__VertexTraitRealProperty__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitRealProperty__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__Group__1"


    // $ANTLR start "rule__VertexTraitRealProperty__Group__1__Impl"
    // InternalMeta.g:1241:1: rule__VertexTraitRealProperty__Group__1__Impl : ( ( rule__VertexTraitRealProperty__Group_1__0 )? ) ;
    public final void rule__VertexTraitRealProperty__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1245:1: ( ( ( rule__VertexTraitRealProperty__Group_1__0 )? ) )
            // InternalMeta.g:1246:1: ( ( rule__VertexTraitRealProperty__Group_1__0 )? )
            {
            // InternalMeta.g:1246:1: ( ( rule__VertexTraitRealProperty__Group_1__0 )? )
            // InternalMeta.g:1247:2: ( rule__VertexTraitRealProperty__Group_1__0 )?
            {
             before(grammarAccess.getVertexTraitRealPropertyAccess().getGroup_1()); 
            // InternalMeta.g:1248:2: ( rule__VertexTraitRealProperty__Group_1__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==33) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalMeta.g:1248:3: rule__VertexTraitRealProperty__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitRealProperty__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVertexTraitRealPropertyAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__Group__1__Impl"


    // $ANTLR start "rule__VertexTraitRealProperty__Group_1__0"
    // InternalMeta.g:1257:1: rule__VertexTraitRealProperty__Group_1__0 : rule__VertexTraitRealProperty__Group_1__0__Impl rule__VertexTraitRealProperty__Group_1__1 ;
    public final void rule__VertexTraitRealProperty__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1261:1: ( rule__VertexTraitRealProperty__Group_1__0__Impl rule__VertexTraitRealProperty__Group_1__1 )
            // InternalMeta.g:1262:2: rule__VertexTraitRealProperty__Group_1__0__Impl rule__VertexTraitRealProperty__Group_1__1
            {
            pushFollow(FOLLOW_17);
            rule__VertexTraitRealProperty__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitRealProperty__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__Group_1__0"


    // $ANTLR start "rule__VertexTraitRealProperty__Group_1__0__Impl"
    // InternalMeta.g:1269:1: rule__VertexTraitRealProperty__Group_1__0__Impl : ( '=' ) ;
    public final void rule__VertexTraitRealProperty__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1273:1: ( ( '=' ) )
            // InternalMeta.g:1274:1: ( '=' )
            {
            // InternalMeta.g:1274:1: ( '=' )
            // InternalMeta.g:1275:2: '='
            {
             before(grammarAccess.getVertexTraitRealPropertyAccess().getEqualsSignKeyword_1_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getVertexTraitRealPropertyAccess().getEqualsSignKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__Group_1__0__Impl"


    // $ANTLR start "rule__VertexTraitRealProperty__Group_1__1"
    // InternalMeta.g:1284:1: rule__VertexTraitRealProperty__Group_1__1 : rule__VertexTraitRealProperty__Group_1__1__Impl ;
    public final void rule__VertexTraitRealProperty__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1288:1: ( rule__VertexTraitRealProperty__Group_1__1__Impl )
            // InternalMeta.g:1289:2: rule__VertexTraitRealProperty__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitRealProperty__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__Group_1__1"


    // $ANTLR start "rule__VertexTraitRealProperty__Group_1__1__Impl"
    // InternalMeta.g:1295:1: rule__VertexTraitRealProperty__Group_1__1__Impl : ( ( rule__VertexTraitRealProperty__DefaultAssignment_1_1 ) ) ;
    public final void rule__VertexTraitRealProperty__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1299:1: ( ( ( rule__VertexTraitRealProperty__DefaultAssignment_1_1 ) ) )
            // InternalMeta.g:1300:1: ( ( rule__VertexTraitRealProperty__DefaultAssignment_1_1 ) )
            {
            // InternalMeta.g:1300:1: ( ( rule__VertexTraitRealProperty__DefaultAssignment_1_1 ) )
            // InternalMeta.g:1301:2: ( rule__VertexTraitRealProperty__DefaultAssignment_1_1 )
            {
             before(grammarAccess.getVertexTraitRealPropertyAccess().getDefaultAssignment_1_1()); 
            // InternalMeta.g:1302:2: ( rule__VertexTraitRealProperty__DefaultAssignment_1_1 )
            // InternalMeta.g:1302:3: rule__VertexTraitRealProperty__DefaultAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitRealProperty__DefaultAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitRealPropertyAccess().getDefaultAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__Group_1__1__Impl"


    // $ANTLR start "rule__VertexTraitBoolProperty__Group__0"
    // InternalMeta.g:1311:1: rule__VertexTraitBoolProperty__Group__0 : rule__VertexTraitBoolProperty__Group__0__Impl rule__VertexTraitBoolProperty__Group__1 ;
    public final void rule__VertexTraitBoolProperty__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1315:1: ( rule__VertexTraitBoolProperty__Group__0__Impl rule__VertexTraitBoolProperty__Group__1 )
            // InternalMeta.g:1316:2: rule__VertexTraitBoolProperty__Group__0__Impl rule__VertexTraitBoolProperty__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__VertexTraitBoolProperty__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitBoolProperty__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__Group__0"


    // $ANTLR start "rule__VertexTraitBoolProperty__Group__0__Impl"
    // InternalMeta.g:1323:1: rule__VertexTraitBoolProperty__Group__0__Impl : ( ( rule__VertexTraitBoolProperty__Alternatives_0 ) ) ;
    public final void rule__VertexTraitBoolProperty__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1327:1: ( ( ( rule__VertexTraitBoolProperty__Alternatives_0 ) ) )
            // InternalMeta.g:1328:1: ( ( rule__VertexTraitBoolProperty__Alternatives_0 ) )
            {
            // InternalMeta.g:1328:1: ( ( rule__VertexTraitBoolProperty__Alternatives_0 ) )
            // InternalMeta.g:1329:2: ( rule__VertexTraitBoolProperty__Alternatives_0 )
            {
             before(grammarAccess.getVertexTraitBoolPropertyAccess().getAlternatives_0()); 
            // InternalMeta.g:1330:2: ( rule__VertexTraitBoolProperty__Alternatives_0 )
            // InternalMeta.g:1330:3: rule__VertexTraitBoolProperty__Alternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitBoolProperty__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitBoolPropertyAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__Group__0__Impl"


    // $ANTLR start "rule__VertexTraitBoolProperty__Group__1"
    // InternalMeta.g:1338:1: rule__VertexTraitBoolProperty__Group__1 : rule__VertexTraitBoolProperty__Group__1__Impl ;
    public final void rule__VertexTraitBoolProperty__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1342:1: ( rule__VertexTraitBoolProperty__Group__1__Impl )
            // InternalMeta.g:1343:2: rule__VertexTraitBoolProperty__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitBoolProperty__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__Group__1"


    // $ANTLR start "rule__VertexTraitBoolProperty__Group__1__Impl"
    // InternalMeta.g:1349:1: rule__VertexTraitBoolProperty__Group__1__Impl : ( ( rule__VertexTraitBoolProperty__Group_1__0 )? ) ;
    public final void rule__VertexTraitBoolProperty__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1353:1: ( ( ( rule__VertexTraitBoolProperty__Group_1__0 )? ) )
            // InternalMeta.g:1354:1: ( ( rule__VertexTraitBoolProperty__Group_1__0 )? )
            {
            // InternalMeta.g:1354:1: ( ( rule__VertexTraitBoolProperty__Group_1__0 )? )
            // InternalMeta.g:1355:2: ( rule__VertexTraitBoolProperty__Group_1__0 )?
            {
             before(grammarAccess.getVertexTraitBoolPropertyAccess().getGroup_1()); 
            // InternalMeta.g:1356:2: ( rule__VertexTraitBoolProperty__Group_1__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==33) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalMeta.g:1356:3: rule__VertexTraitBoolProperty__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitBoolProperty__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVertexTraitBoolPropertyAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__Group__1__Impl"


    // $ANTLR start "rule__VertexTraitBoolProperty__Group_1__0"
    // InternalMeta.g:1365:1: rule__VertexTraitBoolProperty__Group_1__0 : rule__VertexTraitBoolProperty__Group_1__0__Impl rule__VertexTraitBoolProperty__Group_1__1 ;
    public final void rule__VertexTraitBoolProperty__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1369:1: ( rule__VertexTraitBoolProperty__Group_1__0__Impl rule__VertexTraitBoolProperty__Group_1__1 )
            // InternalMeta.g:1370:2: rule__VertexTraitBoolProperty__Group_1__0__Impl rule__VertexTraitBoolProperty__Group_1__1
            {
            pushFollow(FOLLOW_18);
            rule__VertexTraitBoolProperty__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitBoolProperty__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__Group_1__0"


    // $ANTLR start "rule__VertexTraitBoolProperty__Group_1__0__Impl"
    // InternalMeta.g:1377:1: rule__VertexTraitBoolProperty__Group_1__0__Impl : ( '=' ) ;
    public final void rule__VertexTraitBoolProperty__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1381:1: ( ( '=' ) )
            // InternalMeta.g:1382:1: ( '=' )
            {
            // InternalMeta.g:1382:1: ( '=' )
            // InternalMeta.g:1383:2: '='
            {
             before(grammarAccess.getVertexTraitBoolPropertyAccess().getEqualsSignKeyword_1_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getVertexTraitBoolPropertyAccess().getEqualsSignKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__Group_1__0__Impl"


    // $ANTLR start "rule__VertexTraitBoolProperty__Group_1__1"
    // InternalMeta.g:1392:1: rule__VertexTraitBoolProperty__Group_1__1 : rule__VertexTraitBoolProperty__Group_1__1__Impl ;
    public final void rule__VertexTraitBoolProperty__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1396:1: ( rule__VertexTraitBoolProperty__Group_1__1__Impl )
            // InternalMeta.g:1397:2: rule__VertexTraitBoolProperty__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitBoolProperty__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__Group_1__1"


    // $ANTLR start "rule__VertexTraitBoolProperty__Group_1__1__Impl"
    // InternalMeta.g:1403:1: rule__VertexTraitBoolProperty__Group_1__1__Impl : ( ( rule__VertexTraitBoolProperty__DefaultAssignment_1_1 ) ) ;
    public final void rule__VertexTraitBoolProperty__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1407:1: ( ( ( rule__VertexTraitBoolProperty__DefaultAssignment_1_1 ) ) )
            // InternalMeta.g:1408:1: ( ( rule__VertexTraitBoolProperty__DefaultAssignment_1_1 ) )
            {
            // InternalMeta.g:1408:1: ( ( rule__VertexTraitBoolProperty__DefaultAssignment_1_1 ) )
            // InternalMeta.g:1409:2: ( rule__VertexTraitBoolProperty__DefaultAssignment_1_1 )
            {
             before(grammarAccess.getVertexTraitBoolPropertyAccess().getDefaultAssignment_1_1()); 
            // InternalMeta.g:1410:2: ( rule__VertexTraitBoolProperty__DefaultAssignment_1_1 )
            // InternalMeta.g:1410:3: rule__VertexTraitBoolProperty__DefaultAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitBoolProperty__DefaultAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitBoolPropertyAccess().getDefaultAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__Group_1__1__Impl"


    // $ANTLR start "rule__VertexTraitStringProperty__Group__0"
    // InternalMeta.g:1419:1: rule__VertexTraitStringProperty__Group__0 : rule__VertexTraitStringProperty__Group__0__Impl rule__VertexTraitStringProperty__Group__1 ;
    public final void rule__VertexTraitStringProperty__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1423:1: ( rule__VertexTraitStringProperty__Group__0__Impl rule__VertexTraitStringProperty__Group__1 )
            // InternalMeta.g:1424:2: rule__VertexTraitStringProperty__Group__0__Impl rule__VertexTraitStringProperty__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__VertexTraitStringProperty__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitStringProperty__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__Group__0"


    // $ANTLR start "rule__VertexTraitStringProperty__Group__0__Impl"
    // InternalMeta.g:1431:1: rule__VertexTraitStringProperty__Group__0__Impl : ( ( rule__VertexTraitStringProperty__Alternatives_0 ) ) ;
    public final void rule__VertexTraitStringProperty__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1435:1: ( ( ( rule__VertexTraitStringProperty__Alternatives_0 ) ) )
            // InternalMeta.g:1436:1: ( ( rule__VertexTraitStringProperty__Alternatives_0 ) )
            {
            // InternalMeta.g:1436:1: ( ( rule__VertexTraitStringProperty__Alternatives_0 ) )
            // InternalMeta.g:1437:2: ( rule__VertexTraitStringProperty__Alternatives_0 )
            {
             before(grammarAccess.getVertexTraitStringPropertyAccess().getAlternatives_0()); 
            // InternalMeta.g:1438:2: ( rule__VertexTraitStringProperty__Alternatives_0 )
            // InternalMeta.g:1438:3: rule__VertexTraitStringProperty__Alternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitStringProperty__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitStringPropertyAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__Group__0__Impl"


    // $ANTLR start "rule__VertexTraitStringProperty__Group__1"
    // InternalMeta.g:1446:1: rule__VertexTraitStringProperty__Group__1 : rule__VertexTraitStringProperty__Group__1__Impl ;
    public final void rule__VertexTraitStringProperty__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1450:1: ( rule__VertexTraitStringProperty__Group__1__Impl )
            // InternalMeta.g:1451:2: rule__VertexTraitStringProperty__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitStringProperty__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__Group__1"


    // $ANTLR start "rule__VertexTraitStringProperty__Group__1__Impl"
    // InternalMeta.g:1457:1: rule__VertexTraitStringProperty__Group__1__Impl : ( ( rule__VertexTraitStringProperty__Group_1__0 )? ) ;
    public final void rule__VertexTraitStringProperty__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1461:1: ( ( ( rule__VertexTraitStringProperty__Group_1__0 )? ) )
            // InternalMeta.g:1462:1: ( ( rule__VertexTraitStringProperty__Group_1__0 )? )
            {
            // InternalMeta.g:1462:1: ( ( rule__VertexTraitStringProperty__Group_1__0 )? )
            // InternalMeta.g:1463:2: ( rule__VertexTraitStringProperty__Group_1__0 )?
            {
             before(grammarAccess.getVertexTraitStringPropertyAccess().getGroup_1()); 
            // InternalMeta.g:1464:2: ( rule__VertexTraitStringProperty__Group_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==33) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalMeta.g:1464:3: rule__VertexTraitStringProperty__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitStringProperty__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVertexTraitStringPropertyAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__Group__1__Impl"


    // $ANTLR start "rule__VertexTraitStringProperty__Group_1__0"
    // InternalMeta.g:1473:1: rule__VertexTraitStringProperty__Group_1__0 : rule__VertexTraitStringProperty__Group_1__0__Impl rule__VertexTraitStringProperty__Group_1__1 ;
    public final void rule__VertexTraitStringProperty__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1477:1: ( rule__VertexTraitStringProperty__Group_1__0__Impl rule__VertexTraitStringProperty__Group_1__1 )
            // InternalMeta.g:1478:2: rule__VertexTraitStringProperty__Group_1__0__Impl rule__VertexTraitStringProperty__Group_1__1
            {
            pushFollow(FOLLOW_17);
            rule__VertexTraitStringProperty__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitStringProperty__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__Group_1__0"


    // $ANTLR start "rule__VertexTraitStringProperty__Group_1__0__Impl"
    // InternalMeta.g:1485:1: rule__VertexTraitStringProperty__Group_1__0__Impl : ( '=' ) ;
    public final void rule__VertexTraitStringProperty__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1489:1: ( ( '=' ) )
            // InternalMeta.g:1490:1: ( '=' )
            {
            // InternalMeta.g:1490:1: ( '=' )
            // InternalMeta.g:1491:2: '='
            {
             before(grammarAccess.getVertexTraitStringPropertyAccess().getEqualsSignKeyword_1_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getVertexTraitStringPropertyAccess().getEqualsSignKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__Group_1__0__Impl"


    // $ANTLR start "rule__VertexTraitStringProperty__Group_1__1"
    // InternalMeta.g:1500:1: rule__VertexTraitStringProperty__Group_1__1 : rule__VertexTraitStringProperty__Group_1__1__Impl ;
    public final void rule__VertexTraitStringProperty__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1504:1: ( rule__VertexTraitStringProperty__Group_1__1__Impl )
            // InternalMeta.g:1505:2: rule__VertexTraitStringProperty__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitStringProperty__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__Group_1__1"


    // $ANTLR start "rule__VertexTraitStringProperty__Group_1__1__Impl"
    // InternalMeta.g:1511:1: rule__VertexTraitStringProperty__Group_1__1__Impl : ( ( rule__VertexTraitStringProperty__DefaultAssignment_1_1 ) ) ;
    public final void rule__VertexTraitStringProperty__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1515:1: ( ( ( rule__VertexTraitStringProperty__DefaultAssignment_1_1 ) ) )
            // InternalMeta.g:1516:1: ( ( rule__VertexTraitStringProperty__DefaultAssignment_1_1 ) )
            {
            // InternalMeta.g:1516:1: ( ( rule__VertexTraitStringProperty__DefaultAssignment_1_1 ) )
            // InternalMeta.g:1517:2: ( rule__VertexTraitStringProperty__DefaultAssignment_1_1 )
            {
             before(grammarAccess.getVertexTraitStringPropertyAccess().getDefaultAssignment_1_1()); 
            // InternalMeta.g:1518:2: ( rule__VertexTraitStringProperty__DefaultAssignment_1_1 )
            // InternalMeta.g:1518:3: rule__VertexTraitStringProperty__DefaultAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitStringProperty__DefaultAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitStringPropertyAccess().getDefaultAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__Group_1__1__Impl"


    // $ANTLR start "rule__VertexTraitArrayProperty__Group__0"
    // InternalMeta.g:1527:1: rule__VertexTraitArrayProperty__Group__0 : rule__VertexTraitArrayProperty__Group__0__Impl rule__VertexTraitArrayProperty__Group__1 ;
    public final void rule__VertexTraitArrayProperty__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1531:1: ( rule__VertexTraitArrayProperty__Group__0__Impl rule__VertexTraitArrayProperty__Group__1 )
            // InternalMeta.g:1532:2: rule__VertexTraitArrayProperty__Group__0__Impl rule__VertexTraitArrayProperty__Group__1
            {
            pushFollow(FOLLOW_19);
            rule__VertexTraitArrayProperty__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitArrayProperty__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitArrayProperty__Group__0"


    // $ANTLR start "rule__VertexTraitArrayProperty__Group__0__Impl"
    // InternalMeta.g:1539:1: rule__VertexTraitArrayProperty__Group__0__Impl : ( 'array' ) ;
    public final void rule__VertexTraitArrayProperty__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1543:1: ( ( 'array' ) )
            // InternalMeta.g:1544:1: ( 'array' )
            {
            // InternalMeta.g:1544:1: ( 'array' )
            // InternalMeta.g:1545:2: 'array'
            {
             before(grammarAccess.getVertexTraitArrayPropertyAccess().getArrayKeyword_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getVertexTraitArrayPropertyAccess().getArrayKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitArrayProperty__Group__0__Impl"


    // $ANTLR start "rule__VertexTraitArrayProperty__Group__1"
    // InternalMeta.g:1554:1: rule__VertexTraitArrayProperty__Group__1 : rule__VertexTraitArrayProperty__Group__1__Impl rule__VertexTraitArrayProperty__Group__2 ;
    public final void rule__VertexTraitArrayProperty__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1558:1: ( rule__VertexTraitArrayProperty__Group__1__Impl rule__VertexTraitArrayProperty__Group__2 )
            // InternalMeta.g:1559:2: rule__VertexTraitArrayProperty__Group__1__Impl rule__VertexTraitArrayProperty__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__VertexTraitArrayProperty__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitArrayProperty__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitArrayProperty__Group__1"


    // $ANTLR start "rule__VertexTraitArrayProperty__Group__1__Impl"
    // InternalMeta.g:1566:1: rule__VertexTraitArrayProperty__Group__1__Impl : ( '<' ) ;
    public final void rule__VertexTraitArrayProperty__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1570:1: ( ( '<' ) )
            // InternalMeta.g:1571:1: ( '<' )
            {
            // InternalMeta.g:1571:1: ( '<' )
            // InternalMeta.g:1572:2: '<'
            {
             before(grammarAccess.getVertexTraitArrayPropertyAccess().getLessThanSignKeyword_1()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getVertexTraitArrayPropertyAccess().getLessThanSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitArrayProperty__Group__1__Impl"


    // $ANTLR start "rule__VertexTraitArrayProperty__Group__2"
    // InternalMeta.g:1581:1: rule__VertexTraitArrayProperty__Group__2 : rule__VertexTraitArrayProperty__Group__2__Impl rule__VertexTraitArrayProperty__Group__3 ;
    public final void rule__VertexTraitArrayProperty__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1585:1: ( rule__VertexTraitArrayProperty__Group__2__Impl rule__VertexTraitArrayProperty__Group__3 )
            // InternalMeta.g:1586:2: rule__VertexTraitArrayProperty__Group__2__Impl rule__VertexTraitArrayProperty__Group__3
            {
            pushFollow(FOLLOW_20);
            rule__VertexTraitArrayProperty__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitArrayProperty__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitArrayProperty__Group__2"


    // $ANTLR start "rule__VertexTraitArrayProperty__Group__2__Impl"
    // InternalMeta.g:1593:1: rule__VertexTraitArrayProperty__Group__2__Impl : ( ( rule__VertexTraitArrayProperty__ItemTypeAssignment_2 ) ) ;
    public final void rule__VertexTraitArrayProperty__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1597:1: ( ( ( rule__VertexTraitArrayProperty__ItemTypeAssignment_2 ) ) )
            // InternalMeta.g:1598:1: ( ( rule__VertexTraitArrayProperty__ItemTypeAssignment_2 ) )
            {
            // InternalMeta.g:1598:1: ( ( rule__VertexTraitArrayProperty__ItemTypeAssignment_2 ) )
            // InternalMeta.g:1599:2: ( rule__VertexTraitArrayProperty__ItemTypeAssignment_2 )
            {
             before(grammarAccess.getVertexTraitArrayPropertyAccess().getItemTypeAssignment_2()); 
            // InternalMeta.g:1600:2: ( rule__VertexTraitArrayProperty__ItemTypeAssignment_2 )
            // InternalMeta.g:1600:3: rule__VertexTraitArrayProperty__ItemTypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitArrayProperty__ItemTypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitArrayPropertyAccess().getItemTypeAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitArrayProperty__Group__2__Impl"


    // $ANTLR start "rule__VertexTraitArrayProperty__Group__3"
    // InternalMeta.g:1608:1: rule__VertexTraitArrayProperty__Group__3 : rule__VertexTraitArrayProperty__Group__3__Impl ;
    public final void rule__VertexTraitArrayProperty__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1612:1: ( rule__VertexTraitArrayProperty__Group__3__Impl )
            // InternalMeta.g:1613:2: rule__VertexTraitArrayProperty__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitArrayProperty__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitArrayProperty__Group__3"


    // $ANTLR start "rule__VertexTraitArrayProperty__Group__3__Impl"
    // InternalMeta.g:1619:1: rule__VertexTraitArrayProperty__Group__3__Impl : ( '>' ) ;
    public final void rule__VertexTraitArrayProperty__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1623:1: ( ( '>' ) )
            // InternalMeta.g:1624:1: ( '>' )
            {
            // InternalMeta.g:1624:1: ( '>' )
            // InternalMeta.g:1625:2: '>'
            {
             before(grammarAccess.getVertexTraitArrayPropertyAccess().getGreaterThanSignKeyword_3()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getVertexTraitArrayPropertyAccess().getGreaterThanSignKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitArrayProperty__Group__3__Impl"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__0"
    // InternalMeta.g:1635:1: rule__VertexTraitObjectProperty__Group__0 : rule__VertexTraitObjectProperty__Group__0__Impl rule__VertexTraitObjectProperty__Group__1 ;
    public final void rule__VertexTraitObjectProperty__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1639:1: ( rule__VertexTraitObjectProperty__Group__0__Impl rule__VertexTraitObjectProperty__Group__1 )
            // InternalMeta.g:1640:2: rule__VertexTraitObjectProperty__Group__0__Impl rule__VertexTraitObjectProperty__Group__1
            {
            pushFollow(FOLLOW_19);
            rule__VertexTraitObjectProperty__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitObjectProperty__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__0"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__0__Impl"
    // InternalMeta.g:1647:1: rule__VertexTraitObjectProperty__Group__0__Impl : ( 'object' ) ;
    public final void rule__VertexTraitObjectProperty__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1651:1: ( ( 'object' ) )
            // InternalMeta.g:1652:1: ( 'object' )
            {
            // InternalMeta.g:1652:1: ( 'object' )
            // InternalMeta.g:1653:2: 'object'
            {
             before(grammarAccess.getVertexTraitObjectPropertyAccess().getObjectKeyword_0()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getVertexTraitObjectPropertyAccess().getObjectKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__0__Impl"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__1"
    // InternalMeta.g:1662:1: rule__VertexTraitObjectProperty__Group__1 : rule__VertexTraitObjectProperty__Group__1__Impl rule__VertexTraitObjectProperty__Group__2 ;
    public final void rule__VertexTraitObjectProperty__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1666:1: ( rule__VertexTraitObjectProperty__Group__1__Impl rule__VertexTraitObjectProperty__Group__2 )
            // InternalMeta.g:1667:2: rule__VertexTraitObjectProperty__Group__1__Impl rule__VertexTraitObjectProperty__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__VertexTraitObjectProperty__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitObjectProperty__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__1"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__1__Impl"
    // InternalMeta.g:1674:1: rule__VertexTraitObjectProperty__Group__1__Impl : ( '<' ) ;
    public final void rule__VertexTraitObjectProperty__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1678:1: ( ( '<' ) )
            // InternalMeta.g:1679:1: ( '<' )
            {
            // InternalMeta.g:1679:1: ( '<' )
            // InternalMeta.g:1680:2: '<'
            {
             before(grammarAccess.getVertexTraitObjectPropertyAccess().getLessThanSignKeyword_1()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getVertexTraitObjectPropertyAccess().getLessThanSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__1__Impl"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__2"
    // InternalMeta.g:1689:1: rule__VertexTraitObjectProperty__Group__2 : rule__VertexTraitObjectProperty__Group__2__Impl rule__VertexTraitObjectProperty__Group__3 ;
    public final void rule__VertexTraitObjectProperty__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1693:1: ( rule__VertexTraitObjectProperty__Group__2__Impl rule__VertexTraitObjectProperty__Group__3 )
            // InternalMeta.g:1694:2: rule__VertexTraitObjectProperty__Group__2__Impl rule__VertexTraitObjectProperty__Group__3
            {
            pushFollow(FOLLOW_11);
            rule__VertexTraitObjectProperty__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitObjectProperty__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__2"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__2__Impl"
    // InternalMeta.g:1701:1: rule__VertexTraitObjectProperty__Group__2__Impl : ( ( rule__VertexTraitObjectProperty__KeyTypeAssignment_2 ) ) ;
    public final void rule__VertexTraitObjectProperty__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1705:1: ( ( ( rule__VertexTraitObjectProperty__KeyTypeAssignment_2 ) ) )
            // InternalMeta.g:1706:1: ( ( rule__VertexTraitObjectProperty__KeyTypeAssignment_2 ) )
            {
            // InternalMeta.g:1706:1: ( ( rule__VertexTraitObjectProperty__KeyTypeAssignment_2 ) )
            // InternalMeta.g:1707:2: ( rule__VertexTraitObjectProperty__KeyTypeAssignment_2 )
            {
             before(grammarAccess.getVertexTraitObjectPropertyAccess().getKeyTypeAssignment_2()); 
            // InternalMeta.g:1708:2: ( rule__VertexTraitObjectProperty__KeyTypeAssignment_2 )
            // InternalMeta.g:1708:3: rule__VertexTraitObjectProperty__KeyTypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitObjectProperty__KeyTypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitObjectPropertyAccess().getKeyTypeAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__2__Impl"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__3"
    // InternalMeta.g:1716:1: rule__VertexTraitObjectProperty__Group__3 : rule__VertexTraitObjectProperty__Group__3__Impl rule__VertexTraitObjectProperty__Group__4 ;
    public final void rule__VertexTraitObjectProperty__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1720:1: ( rule__VertexTraitObjectProperty__Group__3__Impl rule__VertexTraitObjectProperty__Group__4 )
            // InternalMeta.g:1721:2: rule__VertexTraitObjectProperty__Group__3__Impl rule__VertexTraitObjectProperty__Group__4
            {
            pushFollow(FOLLOW_14);
            rule__VertexTraitObjectProperty__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitObjectProperty__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__3"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__3__Impl"
    // InternalMeta.g:1728:1: rule__VertexTraitObjectProperty__Group__3__Impl : ( ',' ) ;
    public final void rule__VertexTraitObjectProperty__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1732:1: ( ( ',' ) )
            // InternalMeta.g:1733:1: ( ',' )
            {
            // InternalMeta.g:1733:1: ( ',' )
            // InternalMeta.g:1734:2: ','
            {
             before(grammarAccess.getVertexTraitObjectPropertyAccess().getCommaKeyword_3()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getVertexTraitObjectPropertyAccess().getCommaKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__3__Impl"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__4"
    // InternalMeta.g:1743:1: rule__VertexTraitObjectProperty__Group__4 : rule__VertexTraitObjectProperty__Group__4__Impl rule__VertexTraitObjectProperty__Group__5 ;
    public final void rule__VertexTraitObjectProperty__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1747:1: ( rule__VertexTraitObjectProperty__Group__4__Impl rule__VertexTraitObjectProperty__Group__5 )
            // InternalMeta.g:1748:2: rule__VertexTraitObjectProperty__Group__4__Impl rule__VertexTraitObjectProperty__Group__5
            {
            pushFollow(FOLLOW_20);
            rule__VertexTraitObjectProperty__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitObjectProperty__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__4"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__4__Impl"
    // InternalMeta.g:1755:1: rule__VertexTraitObjectProperty__Group__4__Impl : ( ( rule__VertexTraitObjectProperty__ValueTypeAssignment_4 ) ) ;
    public final void rule__VertexTraitObjectProperty__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1759:1: ( ( ( rule__VertexTraitObjectProperty__ValueTypeAssignment_4 ) ) )
            // InternalMeta.g:1760:1: ( ( rule__VertexTraitObjectProperty__ValueTypeAssignment_4 ) )
            {
            // InternalMeta.g:1760:1: ( ( rule__VertexTraitObjectProperty__ValueTypeAssignment_4 ) )
            // InternalMeta.g:1761:2: ( rule__VertexTraitObjectProperty__ValueTypeAssignment_4 )
            {
             before(grammarAccess.getVertexTraitObjectPropertyAccess().getValueTypeAssignment_4()); 
            // InternalMeta.g:1762:2: ( rule__VertexTraitObjectProperty__ValueTypeAssignment_4 )
            // InternalMeta.g:1762:3: rule__VertexTraitObjectProperty__ValueTypeAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitObjectProperty__ValueTypeAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitObjectPropertyAccess().getValueTypeAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__4__Impl"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__5"
    // InternalMeta.g:1770:1: rule__VertexTraitObjectProperty__Group__5 : rule__VertexTraitObjectProperty__Group__5__Impl ;
    public final void rule__VertexTraitObjectProperty__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1774:1: ( rule__VertexTraitObjectProperty__Group__5__Impl )
            // InternalMeta.g:1775:2: rule__VertexTraitObjectProperty__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitObjectProperty__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__5"


    // $ANTLR start "rule__VertexTraitObjectProperty__Group__5__Impl"
    // InternalMeta.g:1781:1: rule__VertexTraitObjectProperty__Group__5__Impl : ( '>' ) ;
    public final void rule__VertexTraitObjectProperty__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1785:1: ( ( '>' ) )
            // InternalMeta.g:1786:1: ( '>' )
            {
            // InternalMeta.g:1786:1: ( '>' )
            // InternalMeta.g:1787:2: '>'
            {
             before(grammarAccess.getVertexTraitObjectPropertyAccess().getGreaterThanSignKeyword_5()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getVertexTraitObjectPropertyAccess().getGreaterThanSignKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__Group__5__Impl"


    // $ANTLR start "rule__VertexTraitPort__Group_2__0"
    // InternalMeta.g:1797:1: rule__VertexTraitPort__Group_2__0 : rule__VertexTraitPort__Group_2__0__Impl rule__VertexTraitPort__Group_2__1 ;
    public final void rule__VertexTraitPort__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1801:1: ( rule__VertexTraitPort__Group_2__0__Impl rule__VertexTraitPort__Group_2__1 )
            // InternalMeta.g:1802:2: rule__VertexTraitPort__Group_2__0__Impl rule__VertexTraitPort__Group_2__1
            {
            pushFollow(FOLLOW_21);
            rule__VertexTraitPort__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__0"


    // $ANTLR start "rule__VertexTraitPort__Group_2__0__Impl"
    // InternalMeta.g:1809:1: rule__VertexTraitPort__Group_2__0__Impl : ( ( rule__VertexTraitPort__DirectionAssignment_2_0 )? ) ;
    public final void rule__VertexTraitPort__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1813:1: ( ( ( rule__VertexTraitPort__DirectionAssignment_2_0 )? ) )
            // InternalMeta.g:1814:1: ( ( rule__VertexTraitPort__DirectionAssignment_2_0 )? )
            {
            // InternalMeta.g:1814:1: ( ( rule__VertexTraitPort__DirectionAssignment_2_0 )? )
            // InternalMeta.g:1815:2: ( rule__VertexTraitPort__DirectionAssignment_2_0 )?
            {
             before(grammarAccess.getVertexTraitPortAccess().getDirectionAssignment_2_0()); 
            // InternalMeta.g:1816:2: ( rule__VertexTraitPort__DirectionAssignment_2_0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=18 && LA18_0<=23)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalMeta.g:1816:3: rule__VertexTraitPort__DirectionAssignment_2_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitPort__DirectionAssignment_2_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVertexTraitPortAccess().getDirectionAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__0__Impl"


    // $ANTLR start "rule__VertexTraitPort__Group_2__1"
    // InternalMeta.g:1824:1: rule__VertexTraitPort__Group_2__1 : rule__VertexTraitPort__Group_2__1__Impl rule__VertexTraitPort__Group_2__2 ;
    public final void rule__VertexTraitPort__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1828:1: ( rule__VertexTraitPort__Group_2__1__Impl rule__VertexTraitPort__Group_2__2 )
            // InternalMeta.g:1829:2: rule__VertexTraitPort__Group_2__1__Impl rule__VertexTraitPort__Group_2__2
            {
            pushFollow(FOLLOW_4);
            rule__VertexTraitPort__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__1"


    // $ANTLR start "rule__VertexTraitPort__Group_2__1__Impl"
    // InternalMeta.g:1836:1: rule__VertexTraitPort__Group_2__1__Impl : ( 'port' ) ;
    public final void rule__VertexTraitPort__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1840:1: ( ( 'port' ) )
            // InternalMeta.g:1841:1: ( 'port' )
            {
            // InternalMeta.g:1841:1: ( 'port' )
            // InternalMeta.g:1842:2: 'port'
            {
             before(grammarAccess.getVertexTraitPortAccess().getPortKeyword_2_1()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getVertexTraitPortAccess().getPortKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__1__Impl"


    // $ANTLR start "rule__VertexTraitPort__Group_2__2"
    // InternalMeta.g:1851:1: rule__VertexTraitPort__Group_2__2 : rule__VertexTraitPort__Group_2__2__Impl rule__VertexTraitPort__Group_2__3 ;
    public final void rule__VertexTraitPort__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1855:1: ( rule__VertexTraitPort__Group_2__2__Impl rule__VertexTraitPort__Group_2__3 )
            // InternalMeta.g:1856:2: rule__VertexTraitPort__Group_2__2__Impl rule__VertexTraitPort__Group_2__3
            {
            pushFollow(FOLLOW_13);
            rule__VertexTraitPort__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__Group_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__2"


    // $ANTLR start "rule__VertexTraitPort__Group_2__2__Impl"
    // InternalMeta.g:1863:1: rule__VertexTraitPort__Group_2__2__Impl : ( ( rule__VertexTraitPort__NameAssignment_2_2 ) ) ;
    public final void rule__VertexTraitPort__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1867:1: ( ( ( rule__VertexTraitPort__NameAssignment_2_2 ) ) )
            // InternalMeta.g:1868:1: ( ( rule__VertexTraitPort__NameAssignment_2_2 ) )
            {
            // InternalMeta.g:1868:1: ( ( rule__VertexTraitPort__NameAssignment_2_2 ) )
            // InternalMeta.g:1869:2: ( rule__VertexTraitPort__NameAssignment_2_2 )
            {
             before(grammarAccess.getVertexTraitPortAccess().getNameAssignment_2_2()); 
            // InternalMeta.g:1870:2: ( rule__VertexTraitPort__NameAssignment_2_2 )
            // InternalMeta.g:1870:3: rule__VertexTraitPort__NameAssignment_2_2
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__NameAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitPortAccess().getNameAssignment_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__2__Impl"


    // $ANTLR start "rule__VertexTraitPort__Group_2__3"
    // InternalMeta.g:1878:1: rule__VertexTraitPort__Group_2__3 : rule__VertexTraitPort__Group_2__3__Impl rule__VertexTraitPort__Group_2__4 ;
    public final void rule__VertexTraitPort__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1882:1: ( rule__VertexTraitPort__Group_2__3__Impl rule__VertexTraitPort__Group_2__4 )
            // InternalMeta.g:1883:2: rule__VertexTraitPort__Group_2__3__Impl rule__VertexTraitPort__Group_2__4
            {
            pushFollow(FOLLOW_4);
            rule__VertexTraitPort__Group_2__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__Group_2__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__3"


    // $ANTLR start "rule__VertexTraitPort__Group_2__3__Impl"
    // InternalMeta.g:1890:1: rule__VertexTraitPort__Group_2__3__Impl : ( ':' ) ;
    public final void rule__VertexTraitPort__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1894:1: ( ( ':' ) )
            // InternalMeta.g:1895:1: ( ':' )
            {
            // InternalMeta.g:1895:1: ( ':' )
            // InternalMeta.g:1896:2: ':'
            {
             before(grammarAccess.getVertexTraitPortAccess().getColonKeyword_2_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getVertexTraitPortAccess().getColonKeyword_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__3__Impl"


    // $ANTLR start "rule__VertexTraitPort__Group_2__4"
    // InternalMeta.g:1905:1: rule__VertexTraitPort__Group_2__4 : rule__VertexTraitPort__Group_2__4__Impl ;
    public final void rule__VertexTraitPort__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1909:1: ( rule__VertexTraitPort__Group_2__4__Impl )
            // InternalMeta.g:1910:2: rule__VertexTraitPort__Group_2__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__Group_2__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__4"


    // $ANTLR start "rule__VertexTraitPort__Group_2__4__Impl"
    // InternalMeta.g:1916:1: rule__VertexTraitPort__Group_2__4__Impl : ( ( rule__VertexTraitPort__TraitAssignment_2_4 ) ) ;
    public final void rule__VertexTraitPort__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1920:1: ( ( ( rule__VertexTraitPort__TraitAssignment_2_4 ) ) )
            // InternalMeta.g:1921:1: ( ( rule__VertexTraitPort__TraitAssignment_2_4 ) )
            {
            // InternalMeta.g:1921:1: ( ( rule__VertexTraitPort__TraitAssignment_2_4 ) )
            // InternalMeta.g:1922:2: ( rule__VertexTraitPort__TraitAssignment_2_4 )
            {
             before(grammarAccess.getVertexTraitPortAccess().getTraitAssignment_2_4()); 
            // InternalMeta.g:1923:2: ( rule__VertexTraitPort__TraitAssignment_2_4 )
            // InternalMeta.g:1923:3: rule__VertexTraitPort__TraitAssignment_2_4
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__TraitAssignment_2_4();

            state._fsp--;


            }

             after(grammarAccess.getVertexTraitPortAccess().getTraitAssignment_2_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__Group_2__4__Impl"


    // $ANTLR start "rule__EdgeTrait__Group__0"
    // InternalMeta.g:1932:1: rule__EdgeTrait__Group__0 : rule__EdgeTrait__Group__0__Impl rule__EdgeTrait__Group__1 ;
    public final void rule__EdgeTrait__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1936:1: ( rule__EdgeTrait__Group__0__Impl rule__EdgeTrait__Group__1 )
            // InternalMeta.g:1937:2: rule__EdgeTrait__Group__0__Impl rule__EdgeTrait__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__EdgeTrait__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__0"


    // $ANTLR start "rule__EdgeTrait__Group__0__Impl"
    // InternalMeta.g:1944:1: rule__EdgeTrait__Group__0__Impl : ( 'edge' ) ;
    public final void rule__EdgeTrait__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1948:1: ( ( 'edge' ) )
            // InternalMeta.g:1949:1: ( 'edge' )
            {
            // InternalMeta.g:1949:1: ( 'edge' )
            // InternalMeta.g:1950:2: 'edge'
            {
             before(grammarAccess.getEdgeTraitAccess().getEdgeKeyword_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitAccess().getEdgeKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__0__Impl"


    // $ANTLR start "rule__EdgeTrait__Group__1"
    // InternalMeta.g:1959:1: rule__EdgeTrait__Group__1 : rule__EdgeTrait__Group__1__Impl rule__EdgeTrait__Group__2 ;
    public final void rule__EdgeTrait__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1963:1: ( rule__EdgeTrait__Group__1__Impl rule__EdgeTrait__Group__2 )
            // InternalMeta.g:1964:2: rule__EdgeTrait__Group__1__Impl rule__EdgeTrait__Group__2
            {
            pushFollow(FOLLOW_22);
            rule__EdgeTrait__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__1"


    // $ANTLR start "rule__EdgeTrait__Group__1__Impl"
    // InternalMeta.g:1971:1: rule__EdgeTrait__Group__1__Impl : ( ( rule__EdgeTrait__NameAssignment_1 ) ) ;
    public final void rule__EdgeTrait__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1975:1: ( ( ( rule__EdgeTrait__NameAssignment_1 ) ) )
            // InternalMeta.g:1976:1: ( ( rule__EdgeTrait__NameAssignment_1 ) )
            {
            // InternalMeta.g:1976:1: ( ( rule__EdgeTrait__NameAssignment_1 ) )
            // InternalMeta.g:1977:2: ( rule__EdgeTrait__NameAssignment_1 )
            {
             before(grammarAccess.getEdgeTraitAccess().getNameAssignment_1()); 
            // InternalMeta.g:1978:2: ( rule__EdgeTrait__NameAssignment_1 )
            // InternalMeta.g:1978:3: rule__EdgeTrait__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTrait__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getEdgeTraitAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__1__Impl"


    // $ANTLR start "rule__EdgeTrait__Group__2"
    // InternalMeta.g:1986:1: rule__EdgeTrait__Group__2 : rule__EdgeTrait__Group__2__Impl rule__EdgeTrait__Group__3 ;
    public final void rule__EdgeTrait__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:1990:1: ( rule__EdgeTrait__Group__2__Impl rule__EdgeTrait__Group__3 )
            // InternalMeta.g:1991:2: rule__EdgeTrait__Group__2__Impl rule__EdgeTrait__Group__3
            {
            pushFollow(FOLLOW_22);
            rule__EdgeTrait__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__2"


    // $ANTLR start "rule__EdgeTrait__Group__2__Impl"
    // InternalMeta.g:1998:1: rule__EdgeTrait__Group__2__Impl : ( ( rule__EdgeTrait__Group_2__0 )? ) ;
    public final void rule__EdgeTrait__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2002:1: ( ( ( rule__EdgeTrait__Group_2__0 )? ) )
            // InternalMeta.g:2003:1: ( ( rule__EdgeTrait__Group_2__0 )? )
            {
            // InternalMeta.g:2003:1: ( ( rule__EdgeTrait__Group_2__0 )? )
            // InternalMeta.g:2004:2: ( rule__EdgeTrait__Group_2__0 )?
            {
             before(grammarAccess.getEdgeTraitAccess().getGroup_2()); 
            // InternalMeta.g:2005:2: ( rule__EdgeTrait__Group_2__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==29) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalMeta.g:2005:3: rule__EdgeTrait__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__EdgeTrait__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEdgeTraitAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__2__Impl"


    // $ANTLR start "rule__EdgeTrait__Group__3"
    // InternalMeta.g:2013:1: rule__EdgeTrait__Group__3 : rule__EdgeTrait__Group__3__Impl rule__EdgeTrait__Group__4 ;
    public final void rule__EdgeTrait__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2017:1: ( rule__EdgeTrait__Group__3__Impl rule__EdgeTrait__Group__4 )
            // InternalMeta.g:2018:2: rule__EdgeTrait__Group__3__Impl rule__EdgeTrait__Group__4
            {
            pushFollow(FOLLOW_23);
            rule__EdgeTrait__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__3"


    // $ANTLR start "rule__EdgeTrait__Group__3__Impl"
    // InternalMeta.g:2025:1: rule__EdgeTrait__Group__3__Impl : ( '{' ) ;
    public final void rule__EdgeTrait__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2029:1: ( ( '{' ) )
            // InternalMeta.g:2030:1: ( '{' )
            {
            // InternalMeta.g:2030:1: ( '{' )
            // InternalMeta.g:2031:2: '{'
            {
             before(grammarAccess.getEdgeTraitAccess().getLeftCurlyBracketKeyword_3()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitAccess().getLeftCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__3__Impl"


    // $ANTLR start "rule__EdgeTrait__Group__4"
    // InternalMeta.g:2040:1: rule__EdgeTrait__Group__4 : rule__EdgeTrait__Group__4__Impl rule__EdgeTrait__Group__5 ;
    public final void rule__EdgeTrait__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2044:1: ( rule__EdgeTrait__Group__4__Impl rule__EdgeTrait__Group__5 )
            // InternalMeta.g:2045:2: rule__EdgeTrait__Group__4__Impl rule__EdgeTrait__Group__5
            {
            pushFollow(FOLLOW_23);
            rule__EdgeTrait__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__4"


    // $ANTLR start "rule__EdgeTrait__Group__4__Impl"
    // InternalMeta.g:2052:1: rule__EdgeTrait__Group__4__Impl : ( ( rule__EdgeTrait__SpecsAssignment_4 )* ) ;
    public final void rule__EdgeTrait__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2056:1: ( ( ( rule__EdgeTrait__SpecsAssignment_4 )* ) )
            // InternalMeta.g:2057:1: ( ( rule__EdgeTrait__SpecsAssignment_4 )* )
            {
            // InternalMeta.g:2057:1: ( ( rule__EdgeTrait__SpecsAssignment_4 )* )
            // InternalMeta.g:2058:2: ( rule__EdgeTrait__SpecsAssignment_4 )*
            {
             before(grammarAccess.getEdgeTraitAccess().getSpecsAssignment_4()); 
            // InternalMeta.g:2059:2: ( rule__EdgeTrait__SpecsAssignment_4 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==40) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalMeta.g:2059:3: rule__EdgeTrait__SpecsAssignment_4
            	    {
            	    pushFollow(FOLLOW_24);
            	    rule__EdgeTrait__SpecsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getEdgeTraitAccess().getSpecsAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__4__Impl"


    // $ANTLR start "rule__EdgeTrait__Group__5"
    // InternalMeta.g:2067:1: rule__EdgeTrait__Group__5 : rule__EdgeTrait__Group__5__Impl ;
    public final void rule__EdgeTrait__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2071:1: ( rule__EdgeTrait__Group__5__Impl )
            // InternalMeta.g:2072:2: rule__EdgeTrait__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__5"


    // $ANTLR start "rule__EdgeTrait__Group__5__Impl"
    // InternalMeta.g:2078:1: rule__EdgeTrait__Group__5__Impl : ( '}' ) ;
    public final void rule__EdgeTrait__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2082:1: ( ( '}' ) )
            // InternalMeta.g:2083:1: ( '}' )
            {
            // InternalMeta.g:2083:1: ( '}' )
            // InternalMeta.g:2084:2: '}'
            {
             before(grammarAccess.getEdgeTraitAccess().getRightCurlyBracketKeyword_5()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group__5__Impl"


    // $ANTLR start "rule__EdgeTrait__Group_2__0"
    // InternalMeta.g:2094:1: rule__EdgeTrait__Group_2__0 : rule__EdgeTrait__Group_2__0__Impl rule__EdgeTrait__Group_2__1 ;
    public final void rule__EdgeTrait__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2098:1: ( rule__EdgeTrait__Group_2__0__Impl rule__EdgeTrait__Group_2__1 )
            // InternalMeta.g:2099:2: rule__EdgeTrait__Group_2__0__Impl rule__EdgeTrait__Group_2__1
            {
            pushFollow(FOLLOW_4);
            rule__EdgeTrait__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2__0"


    // $ANTLR start "rule__EdgeTrait__Group_2__0__Impl"
    // InternalMeta.g:2106:1: rule__EdgeTrait__Group_2__0__Impl : ( 'refines' ) ;
    public final void rule__EdgeTrait__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2110:1: ( ( 'refines' ) )
            // InternalMeta.g:2111:1: ( 'refines' )
            {
            // InternalMeta.g:2111:1: ( 'refines' )
            // InternalMeta.g:2112:2: 'refines'
            {
             before(grammarAccess.getEdgeTraitAccess().getRefinesKeyword_2_0()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitAccess().getRefinesKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2__0__Impl"


    // $ANTLR start "rule__EdgeTrait__Group_2__1"
    // InternalMeta.g:2121:1: rule__EdgeTrait__Group_2__1 : rule__EdgeTrait__Group_2__1__Impl rule__EdgeTrait__Group_2__2 ;
    public final void rule__EdgeTrait__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2125:1: ( rule__EdgeTrait__Group_2__1__Impl rule__EdgeTrait__Group_2__2 )
            // InternalMeta.g:2126:2: rule__EdgeTrait__Group_2__1__Impl rule__EdgeTrait__Group_2__2
            {
            pushFollow(FOLLOW_11);
            rule__EdgeTrait__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2__1"


    // $ANTLR start "rule__EdgeTrait__Group_2__1__Impl"
    // InternalMeta.g:2133:1: rule__EdgeTrait__Group_2__1__Impl : ( ( rule__EdgeTrait__RefinesAssignment_2_1 ) ) ;
    public final void rule__EdgeTrait__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2137:1: ( ( ( rule__EdgeTrait__RefinesAssignment_2_1 ) ) )
            // InternalMeta.g:2138:1: ( ( rule__EdgeTrait__RefinesAssignment_2_1 ) )
            {
            // InternalMeta.g:2138:1: ( ( rule__EdgeTrait__RefinesAssignment_2_1 ) )
            // InternalMeta.g:2139:2: ( rule__EdgeTrait__RefinesAssignment_2_1 )
            {
             before(grammarAccess.getEdgeTraitAccess().getRefinesAssignment_2_1()); 
            // InternalMeta.g:2140:2: ( rule__EdgeTrait__RefinesAssignment_2_1 )
            // InternalMeta.g:2140:3: rule__EdgeTrait__RefinesAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTrait__RefinesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getEdgeTraitAccess().getRefinesAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2__1__Impl"


    // $ANTLR start "rule__EdgeTrait__Group_2__2"
    // InternalMeta.g:2148:1: rule__EdgeTrait__Group_2__2 : rule__EdgeTrait__Group_2__2__Impl ;
    public final void rule__EdgeTrait__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2152:1: ( rule__EdgeTrait__Group_2__2__Impl )
            // InternalMeta.g:2153:2: rule__EdgeTrait__Group_2__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2__2"


    // $ANTLR start "rule__EdgeTrait__Group_2__2__Impl"
    // InternalMeta.g:2159:1: rule__EdgeTrait__Group_2__2__Impl : ( ( rule__EdgeTrait__Group_2_2__0 )* ) ;
    public final void rule__EdgeTrait__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2163:1: ( ( ( rule__EdgeTrait__Group_2_2__0 )* ) )
            // InternalMeta.g:2164:1: ( ( rule__EdgeTrait__Group_2_2__0 )* )
            {
            // InternalMeta.g:2164:1: ( ( rule__EdgeTrait__Group_2_2__0 )* )
            // InternalMeta.g:2165:2: ( rule__EdgeTrait__Group_2_2__0 )*
            {
             before(grammarAccess.getEdgeTraitAccess().getGroup_2_2()); 
            // InternalMeta.g:2166:2: ( rule__EdgeTrait__Group_2_2__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==30) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalMeta.g:2166:3: rule__EdgeTrait__Group_2_2__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__EdgeTrait__Group_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

             after(grammarAccess.getEdgeTraitAccess().getGroup_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2__2__Impl"


    // $ANTLR start "rule__EdgeTrait__Group_2_2__0"
    // InternalMeta.g:2175:1: rule__EdgeTrait__Group_2_2__0 : rule__EdgeTrait__Group_2_2__0__Impl rule__EdgeTrait__Group_2_2__1 ;
    public final void rule__EdgeTrait__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2179:1: ( rule__EdgeTrait__Group_2_2__0__Impl rule__EdgeTrait__Group_2_2__1 )
            // InternalMeta.g:2180:2: rule__EdgeTrait__Group_2_2__0__Impl rule__EdgeTrait__Group_2_2__1
            {
            pushFollow(FOLLOW_4);
            rule__EdgeTrait__Group_2_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group_2_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2_2__0"


    // $ANTLR start "rule__EdgeTrait__Group_2_2__0__Impl"
    // InternalMeta.g:2187:1: rule__EdgeTrait__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__EdgeTrait__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2191:1: ( ( ',' ) )
            // InternalMeta.g:2192:1: ( ',' )
            {
            // InternalMeta.g:2192:1: ( ',' )
            // InternalMeta.g:2193:2: ','
            {
             before(grammarAccess.getEdgeTraitAccess().getCommaKeyword_2_2_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitAccess().getCommaKeyword_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2_2__0__Impl"


    // $ANTLR start "rule__EdgeTrait__Group_2_2__1"
    // InternalMeta.g:2202:1: rule__EdgeTrait__Group_2_2__1 : rule__EdgeTrait__Group_2_2__1__Impl ;
    public final void rule__EdgeTrait__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2206:1: ( rule__EdgeTrait__Group_2_2__1__Impl )
            // InternalMeta.g:2207:2: rule__EdgeTrait__Group_2_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTrait__Group_2_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2_2__1"


    // $ANTLR start "rule__EdgeTrait__Group_2_2__1__Impl"
    // InternalMeta.g:2213:1: rule__EdgeTrait__Group_2_2__1__Impl : ( ( rule__EdgeTrait__RefinesAssignment_2_2_1 ) ) ;
    public final void rule__EdgeTrait__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2217:1: ( ( ( rule__EdgeTrait__RefinesAssignment_2_2_1 ) ) )
            // InternalMeta.g:2218:1: ( ( rule__EdgeTrait__RefinesAssignment_2_2_1 ) )
            {
            // InternalMeta.g:2218:1: ( ( rule__EdgeTrait__RefinesAssignment_2_2_1 ) )
            // InternalMeta.g:2219:2: ( rule__EdgeTrait__RefinesAssignment_2_2_1 )
            {
             before(grammarAccess.getEdgeTraitAccess().getRefinesAssignment_2_2_1()); 
            // InternalMeta.g:2220:2: ( rule__EdgeTrait__RefinesAssignment_2_2_1 )
            // InternalMeta.g:2220:3: rule__EdgeTrait__RefinesAssignment_2_2_1
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTrait__RefinesAssignment_2_2_1();

            state._fsp--;


            }

             after(grammarAccess.getEdgeTraitAccess().getRefinesAssignment_2_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__Group_2_2__1__Impl"


    // $ANTLR start "rule__EdgeTraitConstraint__Group__0"
    // InternalMeta.g:2229:1: rule__EdgeTraitConstraint__Group__0 : rule__EdgeTraitConstraint__Group__0__Impl rule__EdgeTraitConstraint__Group__1 ;
    public final void rule__EdgeTraitConstraint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2233:1: ( rule__EdgeTraitConstraint__Group__0__Impl rule__EdgeTraitConstraint__Group__1 )
            // InternalMeta.g:2234:2: rule__EdgeTraitConstraint__Group__0__Impl rule__EdgeTraitConstraint__Group__1
            {
            pushFollow(FOLLOW_25);
            rule__EdgeTraitConstraint__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group__0"


    // $ANTLR start "rule__EdgeTraitConstraint__Group__0__Impl"
    // InternalMeta.g:2241:1: rule__EdgeTraitConstraint__Group__0__Impl : ( 'constraint' ) ;
    public final void rule__EdgeTraitConstraint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2245:1: ( ( 'constraint' ) )
            // InternalMeta.g:2246:1: ( 'constraint' )
            {
            // InternalMeta.g:2246:1: ( 'constraint' )
            // InternalMeta.g:2247:2: 'constraint'
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getConstraintKeyword_0()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitConstraintAccess().getConstraintKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group__0__Impl"


    // $ANTLR start "rule__EdgeTraitConstraint__Group__1"
    // InternalMeta.g:2256:1: rule__EdgeTraitConstraint__Group__1 : rule__EdgeTraitConstraint__Group__1__Impl rule__EdgeTraitConstraint__Group__2 ;
    public final void rule__EdgeTraitConstraint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2260:1: ( rule__EdgeTraitConstraint__Group__1__Impl rule__EdgeTraitConstraint__Group__2 )
            // InternalMeta.g:2261:2: rule__EdgeTraitConstraint__Group__1__Impl rule__EdgeTraitConstraint__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__EdgeTraitConstraint__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group__1"


    // $ANTLR start "rule__EdgeTraitConstraint__Group__1__Impl"
    // InternalMeta.g:2268:1: rule__EdgeTraitConstraint__Group__1__Impl : ( ( rule__EdgeTraitConstraint__Alternatives_1 ) ) ;
    public final void rule__EdgeTraitConstraint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2272:1: ( ( ( rule__EdgeTraitConstraint__Alternatives_1 ) ) )
            // InternalMeta.g:2273:1: ( ( rule__EdgeTraitConstraint__Alternatives_1 ) )
            {
            // InternalMeta.g:2273:1: ( ( rule__EdgeTraitConstraint__Alternatives_1 ) )
            // InternalMeta.g:2274:2: ( rule__EdgeTraitConstraint__Alternatives_1 )
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getAlternatives_1()); 
            // InternalMeta.g:2275:2: ( rule__EdgeTraitConstraint__Alternatives_1 )
            // InternalMeta.g:2275:3: rule__EdgeTraitConstraint__Alternatives_1
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__Alternatives_1();

            state._fsp--;


            }

             after(grammarAccess.getEdgeTraitConstraintAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group__1__Impl"


    // $ANTLR start "rule__EdgeTraitConstraint__Group__2"
    // InternalMeta.g:2283:1: rule__EdgeTraitConstraint__Group__2 : rule__EdgeTraitConstraint__Group__2__Impl rule__EdgeTraitConstraint__Group__3 ;
    public final void rule__EdgeTraitConstraint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2287:1: ( rule__EdgeTraitConstraint__Group__2__Impl rule__EdgeTraitConstraint__Group__3 )
            // InternalMeta.g:2288:2: rule__EdgeTraitConstraint__Group__2__Impl rule__EdgeTraitConstraint__Group__3
            {
            pushFollow(FOLLOW_26);
            rule__EdgeTraitConstraint__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group__2"


    // $ANTLR start "rule__EdgeTraitConstraint__Group__2__Impl"
    // InternalMeta.g:2295:1: rule__EdgeTraitConstraint__Group__2__Impl : ( ( rule__EdgeTraitConstraint__FilterAssignment_2 ) ) ;
    public final void rule__EdgeTraitConstraint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2299:1: ( ( ( rule__EdgeTraitConstraint__FilterAssignment_2 ) ) )
            // InternalMeta.g:2300:1: ( ( rule__EdgeTraitConstraint__FilterAssignment_2 ) )
            {
            // InternalMeta.g:2300:1: ( ( rule__EdgeTraitConstraint__FilterAssignment_2 ) )
            // InternalMeta.g:2301:2: ( rule__EdgeTraitConstraint__FilterAssignment_2 )
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getFilterAssignment_2()); 
            // InternalMeta.g:2302:2: ( rule__EdgeTraitConstraint__FilterAssignment_2 )
            // InternalMeta.g:2302:3: rule__EdgeTraitConstraint__FilterAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__FilterAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getEdgeTraitConstraintAccess().getFilterAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group__2__Impl"


    // $ANTLR start "rule__EdgeTraitConstraint__Group__3"
    // InternalMeta.g:2310:1: rule__EdgeTraitConstraint__Group__3 : rule__EdgeTraitConstraint__Group__3__Impl ;
    public final void rule__EdgeTraitConstraint__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2314:1: ( rule__EdgeTraitConstraint__Group__3__Impl )
            // InternalMeta.g:2315:2: rule__EdgeTraitConstraint__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group__3"


    // $ANTLR start "rule__EdgeTraitConstraint__Group__3__Impl"
    // InternalMeta.g:2321:1: rule__EdgeTraitConstraint__Group__3__Impl : ( ( rule__EdgeTraitConstraint__Group_3__0 )* ) ;
    public final void rule__EdgeTraitConstraint__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2325:1: ( ( ( rule__EdgeTraitConstraint__Group_3__0 )* ) )
            // InternalMeta.g:2326:1: ( ( rule__EdgeTraitConstraint__Group_3__0 )* )
            {
            // InternalMeta.g:2326:1: ( ( rule__EdgeTraitConstraint__Group_3__0 )* )
            // InternalMeta.g:2327:2: ( rule__EdgeTraitConstraint__Group_3__0 )*
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getGroup_3()); 
            // InternalMeta.g:2328:2: ( rule__EdgeTraitConstraint__Group_3__0 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==41) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalMeta.g:2328:3: rule__EdgeTraitConstraint__Group_3__0
            	    {
            	    pushFollow(FOLLOW_27);
            	    rule__EdgeTraitConstraint__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getEdgeTraitConstraintAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group__3__Impl"


    // $ANTLR start "rule__EdgeTraitConstraint__Group_3__0"
    // InternalMeta.g:2337:1: rule__EdgeTraitConstraint__Group_3__0 : rule__EdgeTraitConstraint__Group_3__0__Impl rule__EdgeTraitConstraint__Group_3__1 ;
    public final void rule__EdgeTraitConstraint__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2341:1: ( rule__EdgeTraitConstraint__Group_3__0__Impl rule__EdgeTraitConstraint__Group_3__1 )
            // InternalMeta.g:2342:2: rule__EdgeTraitConstraint__Group_3__0__Impl rule__EdgeTraitConstraint__Group_3__1
            {
            pushFollow(FOLLOW_4);
            rule__EdgeTraitConstraint__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group_3__0"


    // $ANTLR start "rule__EdgeTraitConstraint__Group_3__0__Impl"
    // InternalMeta.g:2349:1: rule__EdgeTraitConstraint__Group_3__0__Impl : ( 'or' ) ;
    public final void rule__EdgeTraitConstraint__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2353:1: ( ( 'or' ) )
            // InternalMeta.g:2354:1: ( 'or' )
            {
            // InternalMeta.g:2354:1: ( 'or' )
            // InternalMeta.g:2355:2: 'or'
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getOrKeyword_3_0()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitConstraintAccess().getOrKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group_3__0__Impl"


    // $ANTLR start "rule__EdgeTraitConstraint__Group_3__1"
    // InternalMeta.g:2364:1: rule__EdgeTraitConstraint__Group_3__1 : rule__EdgeTraitConstraint__Group_3__1__Impl ;
    public final void rule__EdgeTraitConstraint__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2368:1: ( rule__EdgeTraitConstraint__Group_3__1__Impl )
            // InternalMeta.g:2369:2: rule__EdgeTraitConstraint__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group_3__1"


    // $ANTLR start "rule__EdgeTraitConstraint__Group_3__1__Impl"
    // InternalMeta.g:2375:1: rule__EdgeTraitConstraint__Group_3__1__Impl : ( ( rule__EdgeTraitConstraint__FilterAssignment_3_1 ) ) ;
    public final void rule__EdgeTraitConstraint__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2379:1: ( ( ( rule__EdgeTraitConstraint__FilterAssignment_3_1 ) ) )
            // InternalMeta.g:2380:1: ( ( rule__EdgeTraitConstraint__FilterAssignment_3_1 ) )
            {
            // InternalMeta.g:2380:1: ( ( rule__EdgeTraitConstraint__FilterAssignment_3_1 ) )
            // InternalMeta.g:2381:2: ( rule__EdgeTraitConstraint__FilterAssignment_3_1 )
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getFilterAssignment_3_1()); 
            // InternalMeta.g:2382:2: ( rule__EdgeTraitConstraint__FilterAssignment_3_1 )
            // InternalMeta.g:2382:3: rule__EdgeTraitConstraint__FilterAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__EdgeTraitConstraint__FilterAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getEdgeTraitConstraintAccess().getFilterAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__Group_3__1__Impl"


    // $ANTLR start "rule__VertexTraitPort__UnorderedGroup"
    // InternalMeta.g:2391:1: rule__VertexTraitPort__UnorderedGroup : rule__VertexTraitPort__UnorderedGroup__0 {...}?;
    public final void rule__VertexTraitPort__UnorderedGroup() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup());
        	
        try {
            // InternalMeta.g:2396:1: ( rule__VertexTraitPort__UnorderedGroup__0 {...}?)
            // InternalMeta.g:2397:2: rule__VertexTraitPort__UnorderedGroup__0 {...}?
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__UnorderedGroup__0();

            state._fsp--;

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "rule__VertexTraitPort__UnorderedGroup", "getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup())");
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__UnorderedGroup"


    // $ANTLR start "rule__VertexTraitPort__UnorderedGroup__Impl"
    // InternalMeta.g:2405:1: rule__VertexTraitPort__UnorderedGroup__Impl : ( ({...}? => ( ( ( rule__VertexTraitPort__MultipleAssignment_0 ) ) ) ) | ({...}? => ( ( ( rule__VertexTraitPort__OrderedAssignment_1 ) ) ) ) | ({...}? => ( ( ( rule__VertexTraitPort__Group_2__0 ) ) ) ) ) ;
    public final void rule__VertexTraitPort__UnorderedGroup__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalMeta.g:2410:1: ( ( ({...}? => ( ( ( rule__VertexTraitPort__MultipleAssignment_0 ) ) ) ) | ({...}? => ( ( ( rule__VertexTraitPort__OrderedAssignment_1 ) ) ) ) | ({...}? => ( ( ( rule__VertexTraitPort__Group_2__0 ) ) ) ) ) )
            // InternalMeta.g:2411:3: ( ({...}? => ( ( ( rule__VertexTraitPort__MultipleAssignment_0 ) ) ) ) | ({...}? => ( ( ( rule__VertexTraitPort__OrderedAssignment_1 ) ) ) ) | ({...}? => ( ( ( rule__VertexTraitPort__Group_2__0 ) ) ) ) )
            {
            // InternalMeta.g:2411:3: ( ({...}? => ( ( ( rule__VertexTraitPort__MultipleAssignment_0 ) ) ) ) | ({...}? => ( ( ( rule__VertexTraitPort__OrderedAssignment_1 ) ) ) ) | ({...}? => ( ( ( rule__VertexTraitPort__Group_2__0 ) ) ) ) )
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( LA23_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0) ) {
                alt23=1;
            }
            else if ( LA23_0 == 45 && getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1) ) {
                alt23=2;
            }
            else if ( ( LA23_0 >= 18 && LA23_0 <= 23 || LA23_0 == 38 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {
                alt23=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // InternalMeta.g:2412:3: ({...}? => ( ( ( rule__VertexTraitPort__MultipleAssignment_0 ) ) ) )
                    {
                    // InternalMeta.g:2412:3: ({...}? => ( ( ( rule__VertexTraitPort__MultipleAssignment_0 ) ) ) )
                    // InternalMeta.g:2413:4: {...}? => ( ( ( rule__VertexTraitPort__MultipleAssignment_0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0) ) {
                        throw new FailedPredicateException(input, "rule__VertexTraitPort__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0)");
                    }
                    // InternalMeta.g:2413:109: ( ( ( rule__VertexTraitPort__MultipleAssignment_0 ) ) )
                    // InternalMeta.g:2414:5: ( ( rule__VertexTraitPort__MultipleAssignment_0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0);
                    				

                    					selected = true;
                    				
                    // InternalMeta.g:2420:5: ( ( rule__VertexTraitPort__MultipleAssignment_0 ) )
                    // InternalMeta.g:2421:6: ( rule__VertexTraitPort__MultipleAssignment_0 )
                    {
                     before(grammarAccess.getVertexTraitPortAccess().getMultipleAssignment_0()); 
                    // InternalMeta.g:2422:6: ( rule__VertexTraitPort__MultipleAssignment_0 )
                    // InternalMeta.g:2422:7: rule__VertexTraitPort__MultipleAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitPort__MultipleAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVertexTraitPortAccess().getMultipleAssignment_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:2427:3: ({...}? => ( ( ( rule__VertexTraitPort__OrderedAssignment_1 ) ) ) )
                    {
                    // InternalMeta.g:2427:3: ({...}? => ( ( ( rule__VertexTraitPort__OrderedAssignment_1 ) ) ) )
                    // InternalMeta.g:2428:4: {...}? => ( ( ( rule__VertexTraitPort__OrderedAssignment_1 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1) ) {
                        throw new FailedPredicateException(input, "rule__VertexTraitPort__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1)");
                    }
                    // InternalMeta.g:2428:109: ( ( ( rule__VertexTraitPort__OrderedAssignment_1 ) ) )
                    // InternalMeta.g:2429:5: ( ( rule__VertexTraitPort__OrderedAssignment_1 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1);
                    				

                    					selected = true;
                    				
                    // InternalMeta.g:2435:5: ( ( rule__VertexTraitPort__OrderedAssignment_1 ) )
                    // InternalMeta.g:2436:6: ( rule__VertexTraitPort__OrderedAssignment_1 )
                    {
                     before(grammarAccess.getVertexTraitPortAccess().getOrderedAssignment_1()); 
                    // InternalMeta.g:2437:6: ( rule__VertexTraitPort__OrderedAssignment_1 )
                    // InternalMeta.g:2437:7: rule__VertexTraitPort__OrderedAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitPort__OrderedAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getVertexTraitPortAccess().getOrderedAssignment_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalMeta.g:2442:3: ({...}? => ( ( ( rule__VertexTraitPort__Group_2__0 ) ) ) )
                    {
                    // InternalMeta.g:2442:3: ({...}? => ( ( ( rule__VertexTraitPort__Group_2__0 ) ) ) )
                    // InternalMeta.g:2443:4: {...}? => ( ( ( rule__VertexTraitPort__Group_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {
                        throw new FailedPredicateException(input, "rule__VertexTraitPort__UnorderedGroup__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2)");
                    }
                    // InternalMeta.g:2443:109: ( ( ( rule__VertexTraitPort__Group_2__0 ) ) )
                    // InternalMeta.g:2444:5: ( ( rule__VertexTraitPort__Group_2__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2);
                    				

                    					selected = true;
                    				
                    // InternalMeta.g:2450:5: ( ( rule__VertexTraitPort__Group_2__0 ) )
                    // InternalMeta.g:2451:6: ( rule__VertexTraitPort__Group_2__0 )
                    {
                     before(grammarAccess.getVertexTraitPortAccess().getGroup_2()); 
                    // InternalMeta.g:2452:6: ( rule__VertexTraitPort__Group_2__0 )
                    // InternalMeta.g:2452:7: rule__VertexTraitPort__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitPort__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getVertexTraitPortAccess().getGroup_2()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__UnorderedGroup__Impl"


    // $ANTLR start "rule__VertexTraitPort__UnorderedGroup__0"
    // InternalMeta.g:2465:1: rule__VertexTraitPort__UnorderedGroup__0 : rule__VertexTraitPort__UnorderedGroup__Impl ( rule__VertexTraitPort__UnorderedGroup__1 )? ;
    public final void rule__VertexTraitPort__UnorderedGroup__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2469:1: ( rule__VertexTraitPort__UnorderedGroup__Impl ( rule__VertexTraitPort__UnorderedGroup__1 )? )
            // InternalMeta.g:2470:2: rule__VertexTraitPort__UnorderedGroup__Impl ( rule__VertexTraitPort__UnorderedGroup__1 )?
            {
            pushFollow(FOLLOW_7);
            rule__VertexTraitPort__UnorderedGroup__Impl();

            state._fsp--;

            // InternalMeta.g:2471:2: ( rule__VertexTraitPort__UnorderedGroup__1 )?
            int alt24=2;
            alt24 = dfa24.predict(input);
            switch (alt24) {
                case 1 :
                    // InternalMeta.g:2471:2: rule__VertexTraitPort__UnorderedGroup__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitPort__UnorderedGroup__1();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__UnorderedGroup__0"


    // $ANTLR start "rule__VertexTraitPort__UnorderedGroup__1"
    // InternalMeta.g:2477:1: rule__VertexTraitPort__UnorderedGroup__1 : rule__VertexTraitPort__UnorderedGroup__Impl ( rule__VertexTraitPort__UnorderedGroup__2 )? ;
    public final void rule__VertexTraitPort__UnorderedGroup__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2481:1: ( rule__VertexTraitPort__UnorderedGroup__Impl ( rule__VertexTraitPort__UnorderedGroup__2 )? )
            // InternalMeta.g:2482:2: rule__VertexTraitPort__UnorderedGroup__Impl ( rule__VertexTraitPort__UnorderedGroup__2 )?
            {
            pushFollow(FOLLOW_7);
            rule__VertexTraitPort__UnorderedGroup__Impl();

            state._fsp--;

            // InternalMeta.g:2483:2: ( rule__VertexTraitPort__UnorderedGroup__2 )?
            int alt25=2;
            alt25 = dfa25.predict(input);
            switch (alt25) {
                case 1 :
                    // InternalMeta.g:2483:2: rule__VertexTraitPort__UnorderedGroup__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__VertexTraitPort__UnorderedGroup__2();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__UnorderedGroup__1"


    // $ANTLR start "rule__VertexTraitPort__UnorderedGroup__2"
    // InternalMeta.g:2489:1: rule__VertexTraitPort__UnorderedGroup__2 : rule__VertexTraitPort__UnorderedGroup__Impl ;
    public final void rule__VertexTraitPort__UnorderedGroup__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2493:1: ( rule__VertexTraitPort__UnorderedGroup__Impl )
            // InternalMeta.g:2494:2: rule__VertexTraitPort__UnorderedGroup__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VertexTraitPort__UnorderedGroup__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__UnorderedGroup__2"


    // $ANTLR start "rule__Model__TraitsAssignment"
    // InternalMeta.g:2501:1: rule__Model__TraitsAssignment : ( ( rule__Model__TraitsAlternatives_0 ) ) ;
    public final void rule__Model__TraitsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2505:1: ( ( ( rule__Model__TraitsAlternatives_0 ) ) )
            // InternalMeta.g:2506:2: ( ( rule__Model__TraitsAlternatives_0 ) )
            {
            // InternalMeta.g:2506:2: ( ( rule__Model__TraitsAlternatives_0 ) )
            // InternalMeta.g:2507:3: ( rule__Model__TraitsAlternatives_0 )
            {
             before(grammarAccess.getModelAccess().getTraitsAlternatives_0()); 
            // InternalMeta.g:2508:3: ( rule__Model__TraitsAlternatives_0 )
            // InternalMeta.g:2508:4: rule__Model__TraitsAlternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__Model__TraitsAlternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getTraitsAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__TraitsAssignment"


    // $ANTLR start "rule__VertexTrait__NameAssignment_1"
    // InternalMeta.g:2516:1: rule__VertexTrait__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__VertexTrait__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2520:1: ( ( RULE_ID ) )
            // InternalMeta.g:2521:2: ( RULE_ID )
            {
            // InternalMeta.g:2521:2: ( RULE_ID )
            // InternalMeta.g:2522:3: RULE_ID
            {
             before(grammarAccess.getVertexTraitAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__NameAssignment_1"


    // $ANTLR start "rule__VertexTrait__RefinesAssignment_2_1"
    // InternalMeta.g:2531:1: rule__VertexTrait__RefinesAssignment_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__VertexTrait__RefinesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2535:1: ( ( ( RULE_ID ) ) )
            // InternalMeta.g:2536:2: ( ( RULE_ID ) )
            {
            // InternalMeta.g:2536:2: ( ( RULE_ID ) )
            // InternalMeta.g:2537:3: ( RULE_ID )
            {
             before(grammarAccess.getVertexTraitAccess().getRefinesVertexTraitCrossReference_2_1_0()); 
            // InternalMeta.g:2538:3: ( RULE_ID )
            // InternalMeta.g:2539:4: RULE_ID
            {
             before(grammarAccess.getVertexTraitAccess().getRefinesVertexTraitIDTerminalRuleCall_2_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getRefinesVertexTraitIDTerminalRuleCall_2_1_0_1()); 

            }

             after(grammarAccess.getVertexTraitAccess().getRefinesVertexTraitCrossReference_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__RefinesAssignment_2_1"


    // $ANTLR start "rule__VertexTrait__RefinesAssignment_2_2_1"
    // InternalMeta.g:2550:1: rule__VertexTrait__RefinesAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__VertexTrait__RefinesAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2554:1: ( ( ( RULE_ID ) ) )
            // InternalMeta.g:2555:2: ( ( RULE_ID ) )
            {
            // InternalMeta.g:2555:2: ( ( RULE_ID ) )
            // InternalMeta.g:2556:3: ( RULE_ID )
            {
             before(grammarAccess.getVertexTraitAccess().getRefinesVertexTraitCrossReference_2_2_1_0()); 
            // InternalMeta.g:2557:3: ( RULE_ID )
            // InternalMeta.g:2558:4: RULE_ID
            {
             before(grammarAccess.getVertexTraitAccess().getRefinesVertexTraitIDTerminalRuleCall_2_2_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getVertexTraitAccess().getRefinesVertexTraitIDTerminalRuleCall_2_2_1_0_1()); 

            }

             after(grammarAccess.getVertexTraitAccess().getRefinesVertexTraitCrossReference_2_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__RefinesAssignment_2_2_1"


    // $ANTLR start "rule__VertexTrait__PortsAssignment_4"
    // InternalMeta.g:2569:1: rule__VertexTrait__PortsAssignment_4 : ( ruleVertexTraitPort ) ;
    public final void rule__VertexTrait__PortsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2573:1: ( ( ruleVertexTraitPort ) )
            // InternalMeta.g:2574:2: ( ruleVertexTraitPort )
            {
            // InternalMeta.g:2574:2: ( ruleVertexTraitPort )
            // InternalMeta.g:2575:3: ruleVertexTraitPort
            {
             before(grammarAccess.getVertexTraitAccess().getPortsVertexTraitPortParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleVertexTraitPort();

            state._fsp--;

             after(grammarAccess.getVertexTraitAccess().getPortsVertexTraitPortParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__PortsAssignment_4"


    // $ANTLR start "rule__VertexTrait__PropertiesAssignment_7"
    // InternalMeta.g:2584:1: rule__VertexTrait__PropertiesAssignment_7 : ( ruleVertexTraitProperty ) ;
    public final void rule__VertexTrait__PropertiesAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2588:1: ( ( ruleVertexTraitProperty ) )
            // InternalMeta.g:2589:2: ( ruleVertexTraitProperty )
            {
            // InternalMeta.g:2589:2: ( ruleVertexTraitProperty )
            // InternalMeta.g:2590:3: ruleVertexTraitProperty
            {
             before(grammarAccess.getVertexTraitAccess().getPropertiesVertexTraitPropertyParserRuleCall_7_0()); 
            pushFollow(FOLLOW_2);
            ruleVertexTraitProperty();

            state._fsp--;

             after(grammarAccess.getVertexTraitAccess().getPropertiesVertexTraitPropertyParserRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTrait__PropertiesAssignment_7"


    // $ANTLR start "rule__VertexTraitProperty__NameAssignment_1"
    // InternalMeta.g:2599:1: rule__VertexTraitProperty__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__VertexTraitProperty__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2603:1: ( ( RULE_ID ) )
            // InternalMeta.g:2604:2: ( RULE_ID )
            {
            // InternalMeta.g:2604:2: ( RULE_ID )
            // InternalMeta.g:2605:3: RULE_ID
            {
             before(grammarAccess.getVertexTraitPropertyAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getVertexTraitPropertyAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__NameAssignment_1"


    // $ANTLR start "rule__VertexTraitProperty__TypeAssignment_3"
    // InternalMeta.g:2614:1: rule__VertexTraitProperty__TypeAssignment_3 : ( ruleVertexTraitPropertyType ) ;
    public final void rule__VertexTraitProperty__TypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2618:1: ( ( ruleVertexTraitPropertyType ) )
            // InternalMeta.g:2619:2: ( ruleVertexTraitPropertyType )
            {
            // InternalMeta.g:2619:2: ( ruleVertexTraitPropertyType )
            // InternalMeta.g:2620:3: ruleVertexTraitPropertyType
            {
             before(grammarAccess.getVertexTraitPropertyAccess().getTypeVertexTraitPropertyTypeParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleVertexTraitPropertyType();

            state._fsp--;

             after(grammarAccess.getVertexTraitPropertyAccess().getTypeVertexTraitPropertyTypeParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitProperty__TypeAssignment_3"


    // $ANTLR start "rule__VertexTraitIntProperty__DefaultAssignment_1_1"
    // InternalMeta.g:2629:1: rule__VertexTraitIntProperty__DefaultAssignment_1_1 : ( RULE_INT ) ;
    public final void rule__VertexTraitIntProperty__DefaultAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2633:1: ( ( RULE_INT ) )
            // InternalMeta.g:2634:2: ( RULE_INT )
            {
            // InternalMeta.g:2634:2: ( RULE_INT )
            // InternalMeta.g:2635:3: RULE_INT
            {
             before(grammarAccess.getVertexTraitIntPropertyAccess().getDefaultINTTerminalRuleCall_1_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getVertexTraitIntPropertyAccess().getDefaultINTTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitIntProperty__DefaultAssignment_1_1"


    // $ANTLR start "rule__VertexTraitRealProperty__DoubleAssignment_0_1"
    // InternalMeta.g:2644:1: rule__VertexTraitRealProperty__DoubleAssignment_0_1 : ( ( 'double' ) ) ;
    public final void rule__VertexTraitRealProperty__DoubleAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2648:1: ( ( ( 'double' ) ) )
            // InternalMeta.g:2649:2: ( ( 'double' ) )
            {
            // InternalMeta.g:2649:2: ( ( 'double' ) )
            // InternalMeta.g:2650:3: ( 'double' )
            {
             before(grammarAccess.getVertexTraitRealPropertyAccess().getDoubleDoubleKeyword_0_1_0()); 
            // InternalMeta.g:2651:3: ( 'double' )
            // InternalMeta.g:2652:4: 'double'
            {
             before(grammarAccess.getVertexTraitRealPropertyAccess().getDoubleDoubleKeyword_0_1_0()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getVertexTraitRealPropertyAccess().getDoubleDoubleKeyword_0_1_0()); 

            }

             after(grammarAccess.getVertexTraitRealPropertyAccess().getDoubleDoubleKeyword_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__DoubleAssignment_0_1"


    // $ANTLR start "rule__VertexTraitRealProperty__DefaultAssignment_1_1"
    // InternalMeta.g:2663:1: rule__VertexTraitRealProperty__DefaultAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__VertexTraitRealProperty__DefaultAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2667:1: ( ( RULE_STRING ) )
            // InternalMeta.g:2668:2: ( RULE_STRING )
            {
            // InternalMeta.g:2668:2: ( RULE_STRING )
            // InternalMeta.g:2669:3: RULE_STRING
            {
             before(grammarAccess.getVertexTraitRealPropertyAccess().getDefaultSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getVertexTraitRealPropertyAccess().getDefaultSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitRealProperty__DefaultAssignment_1_1"


    // $ANTLR start "rule__VertexTraitBoolProperty__DefaultAssignment_1_1"
    // InternalMeta.g:2678:1: rule__VertexTraitBoolProperty__DefaultAssignment_1_1 : ( ( 'true' ) ) ;
    public final void rule__VertexTraitBoolProperty__DefaultAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2682:1: ( ( ( 'true' ) ) )
            // InternalMeta.g:2683:2: ( ( 'true' ) )
            {
            // InternalMeta.g:2683:2: ( ( 'true' ) )
            // InternalMeta.g:2684:3: ( 'true' )
            {
             before(grammarAccess.getVertexTraitBoolPropertyAccess().getDefaultTrueKeyword_1_1_0()); 
            // InternalMeta.g:2685:3: ( 'true' )
            // InternalMeta.g:2686:4: 'true'
            {
             before(grammarAccess.getVertexTraitBoolPropertyAccess().getDefaultTrueKeyword_1_1_0()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getVertexTraitBoolPropertyAccess().getDefaultTrueKeyword_1_1_0()); 

            }

             after(grammarAccess.getVertexTraitBoolPropertyAccess().getDefaultTrueKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitBoolProperty__DefaultAssignment_1_1"


    // $ANTLR start "rule__VertexTraitStringProperty__DefaultAssignment_1_1"
    // InternalMeta.g:2697:1: rule__VertexTraitStringProperty__DefaultAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__VertexTraitStringProperty__DefaultAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2701:1: ( ( RULE_STRING ) )
            // InternalMeta.g:2702:2: ( RULE_STRING )
            {
            // InternalMeta.g:2702:2: ( RULE_STRING )
            // InternalMeta.g:2703:3: RULE_STRING
            {
             before(grammarAccess.getVertexTraitStringPropertyAccess().getDefaultSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getVertexTraitStringPropertyAccess().getDefaultSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitStringProperty__DefaultAssignment_1_1"


    // $ANTLR start "rule__VertexTraitArrayProperty__ItemTypeAssignment_2"
    // InternalMeta.g:2712:1: rule__VertexTraitArrayProperty__ItemTypeAssignment_2 : ( ruleVertexTraitPropertyType ) ;
    public final void rule__VertexTraitArrayProperty__ItemTypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2716:1: ( ( ruleVertexTraitPropertyType ) )
            // InternalMeta.g:2717:2: ( ruleVertexTraitPropertyType )
            {
            // InternalMeta.g:2717:2: ( ruleVertexTraitPropertyType )
            // InternalMeta.g:2718:3: ruleVertexTraitPropertyType
            {
             before(grammarAccess.getVertexTraitArrayPropertyAccess().getItemTypeVertexTraitPropertyTypeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleVertexTraitPropertyType();

            state._fsp--;

             after(grammarAccess.getVertexTraitArrayPropertyAccess().getItemTypeVertexTraitPropertyTypeParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitArrayProperty__ItemTypeAssignment_2"


    // $ANTLR start "rule__VertexTraitObjectProperty__KeyTypeAssignment_2"
    // InternalMeta.g:2727:1: rule__VertexTraitObjectProperty__KeyTypeAssignment_2 : ( ruleVertexTraitPropertyType ) ;
    public final void rule__VertexTraitObjectProperty__KeyTypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2731:1: ( ( ruleVertexTraitPropertyType ) )
            // InternalMeta.g:2732:2: ( ruleVertexTraitPropertyType )
            {
            // InternalMeta.g:2732:2: ( ruleVertexTraitPropertyType )
            // InternalMeta.g:2733:3: ruleVertexTraitPropertyType
            {
             before(grammarAccess.getVertexTraitObjectPropertyAccess().getKeyTypeVertexTraitPropertyTypeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleVertexTraitPropertyType();

            state._fsp--;

             after(grammarAccess.getVertexTraitObjectPropertyAccess().getKeyTypeVertexTraitPropertyTypeParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__KeyTypeAssignment_2"


    // $ANTLR start "rule__VertexTraitObjectProperty__ValueTypeAssignment_4"
    // InternalMeta.g:2742:1: rule__VertexTraitObjectProperty__ValueTypeAssignment_4 : ( ruleVertexTraitPropertyType ) ;
    public final void rule__VertexTraitObjectProperty__ValueTypeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2746:1: ( ( ruleVertexTraitPropertyType ) )
            // InternalMeta.g:2747:2: ( ruleVertexTraitPropertyType )
            {
            // InternalMeta.g:2747:2: ( ruleVertexTraitPropertyType )
            // InternalMeta.g:2748:3: ruleVertexTraitPropertyType
            {
             before(grammarAccess.getVertexTraitObjectPropertyAccess().getValueTypeVertexTraitPropertyTypeParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleVertexTraitPropertyType();

            state._fsp--;

             after(grammarAccess.getVertexTraitObjectPropertyAccess().getValueTypeVertexTraitPropertyTypeParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitObjectProperty__ValueTypeAssignment_4"


    // $ANTLR start "rule__VertexTraitPort__MultipleAssignment_0"
    // InternalMeta.g:2757:1: rule__VertexTraitPort__MultipleAssignment_0 : ( ( 'multiple' ) ) ;
    public final void rule__VertexTraitPort__MultipleAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2761:1: ( ( ( 'multiple' ) ) )
            // InternalMeta.g:2762:2: ( ( 'multiple' ) )
            {
            // InternalMeta.g:2762:2: ( ( 'multiple' ) )
            // InternalMeta.g:2763:3: ( 'multiple' )
            {
             before(grammarAccess.getVertexTraitPortAccess().getMultipleMultipleKeyword_0_0()); 
            // InternalMeta.g:2764:3: ( 'multiple' )
            // InternalMeta.g:2765:4: 'multiple'
            {
             before(grammarAccess.getVertexTraitPortAccess().getMultipleMultipleKeyword_0_0()); 
            match(input,44,FOLLOW_2); 
             after(grammarAccess.getVertexTraitPortAccess().getMultipleMultipleKeyword_0_0()); 

            }

             after(grammarAccess.getVertexTraitPortAccess().getMultipleMultipleKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__MultipleAssignment_0"


    // $ANTLR start "rule__VertexTraitPort__OrderedAssignment_1"
    // InternalMeta.g:2776:1: rule__VertexTraitPort__OrderedAssignment_1 : ( ( 'ordered' ) ) ;
    public final void rule__VertexTraitPort__OrderedAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2780:1: ( ( ( 'ordered' ) ) )
            // InternalMeta.g:2781:2: ( ( 'ordered' ) )
            {
            // InternalMeta.g:2781:2: ( ( 'ordered' ) )
            // InternalMeta.g:2782:3: ( 'ordered' )
            {
             before(grammarAccess.getVertexTraitPortAccess().getOrderedOrderedKeyword_1_0()); 
            // InternalMeta.g:2783:3: ( 'ordered' )
            // InternalMeta.g:2784:4: 'ordered'
            {
             before(grammarAccess.getVertexTraitPortAccess().getOrderedOrderedKeyword_1_0()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getVertexTraitPortAccess().getOrderedOrderedKeyword_1_0()); 

            }

             after(grammarAccess.getVertexTraitPortAccess().getOrderedOrderedKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__OrderedAssignment_1"


    // $ANTLR start "rule__VertexTraitPort__DirectionAssignment_2_0"
    // InternalMeta.g:2795:1: rule__VertexTraitPort__DirectionAssignment_2_0 : ( ruleVertexTraitPortDirection ) ;
    public final void rule__VertexTraitPort__DirectionAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2799:1: ( ( ruleVertexTraitPortDirection ) )
            // InternalMeta.g:2800:2: ( ruleVertexTraitPortDirection )
            {
            // InternalMeta.g:2800:2: ( ruleVertexTraitPortDirection )
            // InternalMeta.g:2801:3: ruleVertexTraitPortDirection
            {
             before(grammarAccess.getVertexTraitPortAccess().getDirectionVertexTraitPortDirectionEnumRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleVertexTraitPortDirection();

            state._fsp--;

             after(grammarAccess.getVertexTraitPortAccess().getDirectionVertexTraitPortDirectionEnumRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__DirectionAssignment_2_0"


    // $ANTLR start "rule__VertexTraitPort__NameAssignment_2_2"
    // InternalMeta.g:2810:1: rule__VertexTraitPort__NameAssignment_2_2 : ( RULE_ID ) ;
    public final void rule__VertexTraitPort__NameAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2814:1: ( ( RULE_ID ) )
            // InternalMeta.g:2815:2: ( RULE_ID )
            {
            // InternalMeta.g:2815:2: ( RULE_ID )
            // InternalMeta.g:2816:3: RULE_ID
            {
             before(grammarAccess.getVertexTraitPortAccess().getNameIDTerminalRuleCall_2_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getVertexTraitPortAccess().getNameIDTerminalRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__NameAssignment_2_2"


    // $ANTLR start "rule__VertexTraitPort__TraitAssignment_2_4"
    // InternalMeta.g:2825:1: rule__VertexTraitPort__TraitAssignment_2_4 : ( ( RULE_ID ) ) ;
    public final void rule__VertexTraitPort__TraitAssignment_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2829:1: ( ( ( RULE_ID ) ) )
            // InternalMeta.g:2830:2: ( ( RULE_ID ) )
            {
            // InternalMeta.g:2830:2: ( ( RULE_ID ) )
            // InternalMeta.g:2831:3: ( RULE_ID )
            {
             before(grammarAccess.getVertexTraitPortAccess().getTraitVertexTraitCrossReference_2_4_0()); 
            // InternalMeta.g:2832:3: ( RULE_ID )
            // InternalMeta.g:2833:4: RULE_ID
            {
             before(grammarAccess.getVertexTraitPortAccess().getTraitVertexTraitIDTerminalRuleCall_2_4_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getVertexTraitPortAccess().getTraitVertexTraitIDTerminalRuleCall_2_4_0_1()); 

            }

             after(grammarAccess.getVertexTraitPortAccess().getTraitVertexTraitCrossReference_2_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VertexTraitPort__TraitAssignment_2_4"


    // $ANTLR start "rule__EdgeTrait__NameAssignment_1"
    // InternalMeta.g:2844:1: rule__EdgeTrait__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__EdgeTrait__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2848:1: ( ( RULE_ID ) )
            // InternalMeta.g:2849:2: ( RULE_ID )
            {
            // InternalMeta.g:2849:2: ( RULE_ID )
            // InternalMeta.g:2850:3: RULE_ID
            {
             before(grammarAccess.getEdgeTraitAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__NameAssignment_1"


    // $ANTLR start "rule__EdgeTrait__RefinesAssignment_2_1"
    // InternalMeta.g:2859:1: rule__EdgeTrait__RefinesAssignment_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__EdgeTrait__RefinesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2863:1: ( ( ( RULE_ID ) ) )
            // InternalMeta.g:2864:2: ( ( RULE_ID ) )
            {
            // InternalMeta.g:2864:2: ( ( RULE_ID ) )
            // InternalMeta.g:2865:3: ( RULE_ID )
            {
             before(grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitCrossReference_2_1_0()); 
            // InternalMeta.g:2866:3: ( RULE_ID )
            // InternalMeta.g:2867:4: RULE_ID
            {
             before(grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitIDTerminalRuleCall_2_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitIDTerminalRuleCall_2_1_0_1()); 

            }

             after(grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitCrossReference_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__RefinesAssignment_2_1"


    // $ANTLR start "rule__EdgeTrait__RefinesAssignment_2_2_1"
    // InternalMeta.g:2878:1: rule__EdgeTrait__RefinesAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__EdgeTrait__RefinesAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2882:1: ( ( ( RULE_ID ) ) )
            // InternalMeta.g:2883:2: ( ( RULE_ID ) )
            {
            // InternalMeta.g:2883:2: ( ( RULE_ID ) )
            // InternalMeta.g:2884:3: ( RULE_ID )
            {
             before(grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitCrossReference_2_2_1_0()); 
            // InternalMeta.g:2885:3: ( RULE_ID )
            // InternalMeta.g:2886:4: RULE_ID
            {
             before(grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitIDTerminalRuleCall_2_2_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitIDTerminalRuleCall_2_2_1_0_1()); 

            }

             after(grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitCrossReference_2_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__RefinesAssignment_2_2_1"


    // $ANTLR start "rule__EdgeTrait__SpecsAssignment_4"
    // InternalMeta.g:2897:1: rule__EdgeTrait__SpecsAssignment_4 : ( ruleEdgeTraitConstraint ) ;
    public final void rule__EdgeTrait__SpecsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2901:1: ( ( ruleEdgeTraitConstraint ) )
            // InternalMeta.g:2902:2: ( ruleEdgeTraitConstraint )
            {
            // InternalMeta.g:2902:2: ( ruleEdgeTraitConstraint )
            // InternalMeta.g:2903:3: ruleEdgeTraitConstraint
            {
             before(grammarAccess.getEdgeTraitAccess().getSpecsEdgeTraitConstraintParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleEdgeTraitConstraint();

            state._fsp--;

             after(grammarAccess.getEdgeTraitAccess().getSpecsEdgeTraitConstraintParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTrait__SpecsAssignment_4"


    // $ANTLR start "rule__EdgeTraitConstraint__TargetAssignment_1_0"
    // InternalMeta.g:2912:1: rule__EdgeTraitConstraint__TargetAssignment_1_0 : ( ( 'target' ) ) ;
    public final void rule__EdgeTraitConstraint__TargetAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2916:1: ( ( ( 'target' ) ) )
            // InternalMeta.g:2917:2: ( ( 'target' ) )
            {
            // InternalMeta.g:2917:2: ( ( 'target' ) )
            // InternalMeta.g:2918:3: ( 'target' )
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getTargetTargetKeyword_1_0_0()); 
            // InternalMeta.g:2919:3: ( 'target' )
            // InternalMeta.g:2920:4: 'target'
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getTargetTargetKeyword_1_0_0()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitConstraintAccess().getTargetTargetKeyword_1_0_0()); 

            }

             after(grammarAccess.getEdgeTraitConstraintAccess().getTargetTargetKeyword_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__TargetAssignment_1_0"


    // $ANTLR start "rule__EdgeTraitConstraint__SourceAssignment_1_1"
    // InternalMeta.g:2931:1: rule__EdgeTraitConstraint__SourceAssignment_1_1 : ( ( 'source' ) ) ;
    public final void rule__EdgeTraitConstraint__SourceAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2935:1: ( ( ( 'source' ) ) )
            // InternalMeta.g:2936:2: ( ( 'source' ) )
            {
            // InternalMeta.g:2936:2: ( ( 'source' ) )
            // InternalMeta.g:2937:3: ( 'source' )
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getSourceSourceKeyword_1_1_0()); 
            // InternalMeta.g:2938:3: ( 'source' )
            // InternalMeta.g:2939:4: 'source'
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getSourceSourceKeyword_1_1_0()); 
            match(input,47,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitConstraintAccess().getSourceSourceKeyword_1_1_0()); 

            }

             after(grammarAccess.getEdgeTraitConstraintAccess().getSourceSourceKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__SourceAssignment_1_1"


    // $ANTLR start "rule__EdgeTraitConstraint__FilterAssignment_2"
    // InternalMeta.g:2950:1: rule__EdgeTraitConstraint__FilterAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__EdgeTraitConstraint__FilterAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2954:1: ( ( ( RULE_ID ) ) )
            // InternalMeta.g:2955:2: ( ( RULE_ID ) )
            {
            // InternalMeta.g:2955:2: ( ( RULE_ID ) )
            // InternalMeta.g:2956:3: ( RULE_ID )
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitCrossReference_2_0()); 
            // InternalMeta.g:2957:3: ( RULE_ID )
            // InternalMeta.g:2958:4: RULE_ID
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitIDTerminalRuleCall_2_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitIDTerminalRuleCall_2_0_1()); 

            }

             after(grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitCrossReference_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__FilterAssignment_2"


    // $ANTLR start "rule__EdgeTraitConstraint__FilterAssignment_3_1"
    // InternalMeta.g:2969:1: rule__EdgeTraitConstraint__FilterAssignment_3_1 : ( ( RULE_ID ) ) ;
    public final void rule__EdgeTraitConstraint__FilterAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMeta.g:2973:1: ( ( ( RULE_ID ) ) )
            // InternalMeta.g:2974:2: ( ( RULE_ID ) )
            {
            // InternalMeta.g:2974:2: ( ( RULE_ID ) )
            // InternalMeta.g:2975:3: ( RULE_ID )
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitCrossReference_3_1_0()); 
            // InternalMeta.g:2976:3: ( RULE_ID )
            // InternalMeta.g:2977:4: RULE_ID
            {
             before(grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitIDTerminalRuleCall_3_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitIDTerminalRuleCall_3_1_0_1()); 

            }

             after(grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitCrossReference_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeTraitConstraint__FilterAssignment_3_1"

    // Delegated rules


    protected DFA24 dfa24 = new DFA24(this);
    protected DFA25 dfa25 = new DFA25(this);
    static final String dfa_1s = "\17\uffff";
    static final String dfa_2s = "\1\12\16\uffff";
    static final String dfa_3s = "\1\22\2\0\6\46\1\4\2\uffff\1\40\1\4\1\0";
    static final String dfa_4s = "\1\55\2\0\6\46\1\4\2\uffff\1\40\1\4\1\0";
    static final String dfa_5s = "\12\uffff\1\2\1\1\3\uffff";
    static final String dfa_6s = "\1\uffff\1\2\1\0\13\uffff\1\1}>";
    static final String[] dfa_7s = {
            "\1\3\1\4\1\5\1\6\1\7\1\10\2\uffff\1\12\13\uffff\1\11\5\uffff\1\1\1\2",
            "\1\uffff",
            "\1\uffff",
            "\1\11",
            "\1\11",
            "\1\11",
            "\1\11",
            "\1\11",
            "\1\11",
            "\1\14",
            "",
            "",
            "\1\15",
            "\1\16",
            "\1\uffff"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA24 extends DFA {

        public DFA24(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 24;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "2471:2: ( rule__VertexTraitPort__UnorderedGroup__1 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA24_2 = input.LA(1);

                         
                        int index24_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1) ) {s = 11;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 10;}

                         
                        input.seek(index24_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA24_14 = input.LA(1);

                         
                        int index24_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {s = 11;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 10;}

                         
                        input.seek(index24_14);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA24_1 = input.LA(1);

                         
                        int index24_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0) ) {s = 11;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 10;}

                         
                        input.seek(index24_1);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 24, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA25 extends DFA {

        public DFA25(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "2483:2: ( rule__VertexTraitPort__UnorderedGroup__2 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA25_2 = input.LA(1);

                         
                        int index25_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1) ) {s = 11;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 10;}

                         
                        input.seek(index25_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA25_14 = input.LA(1);

                         
                        int index25_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {s = 11;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 10;}

                         
                        input.seek(index25_14);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA25_1 = input.LA(1);

                         
                        int index25_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0) ) {s = 11;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 10;}

                         
                        input.seek(index25_1);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 25, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000008001000002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000022000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000304004FC0000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000304000FC0002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000090000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x000004240003F800L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000304000FC0000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000028000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000010010000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000020000000002L});

}
