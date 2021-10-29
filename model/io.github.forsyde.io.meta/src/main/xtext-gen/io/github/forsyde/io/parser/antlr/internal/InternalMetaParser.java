package io.github.forsyde.io.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import io.github.forsyde.io.services.MetaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMetaParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'vertex'", "'refines'", "','", "'('", "')'", "'{'", "'}'", "'property'", "':'", "'int'", "'integer'", "'='", "'float'", "'double'", "'bool'", "'boolean'", "'true'", "'string'", "'str'", "'array'", "'<'", "'>'", "'object'", "'multiple'", "'ordered'", "'port'", "'edge'", "'constraint'", "'target'", "'source'", "'or'", "'inout'", "'outin'", "'out'", "'outgoing'", "'in'", "'incoming'"
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

        public InternalMetaParser(TokenStream input, MetaGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected MetaGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalMeta.g:65:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalMeta.g:65:46: (iv_ruleModel= ruleModel EOF )
            // InternalMeta.g:66:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalMeta.g:72:1: ruleModel returns [EObject current=null] : ( ( (lv_traits_0_1= ruleVertexTrait | lv_traits_0_2= ruleEdgeTrait ) ) )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_traits_0_1 = null;

        EObject lv_traits_0_2 = null;



        	enterRule();

        try {
            // InternalMeta.g:78:2: ( ( ( (lv_traits_0_1= ruleVertexTrait | lv_traits_0_2= ruleEdgeTrait ) ) )* )
            // InternalMeta.g:79:2: ( ( (lv_traits_0_1= ruleVertexTrait | lv_traits_0_2= ruleEdgeTrait ) ) )*
            {
            // InternalMeta.g:79:2: ( ( (lv_traits_0_1= ruleVertexTrait | lv_traits_0_2= ruleEdgeTrait ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11||LA2_0==37) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalMeta.g:80:3: ( (lv_traits_0_1= ruleVertexTrait | lv_traits_0_2= ruleEdgeTrait ) )
            	    {
            	    // InternalMeta.g:80:3: ( (lv_traits_0_1= ruleVertexTrait | lv_traits_0_2= ruleEdgeTrait ) )
            	    // InternalMeta.g:81:4: (lv_traits_0_1= ruleVertexTrait | lv_traits_0_2= ruleEdgeTrait )
            	    {
            	    // InternalMeta.g:81:4: (lv_traits_0_1= ruleVertexTrait | lv_traits_0_2= ruleEdgeTrait )
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==11) ) {
            	        alt1=1;
            	    }
            	    else if ( (LA1_0==37) ) {
            	        alt1=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 1, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // InternalMeta.g:82:5: lv_traits_0_1= ruleVertexTrait
            	            {

            	            					newCompositeNode(grammarAccess.getModelAccess().getTraitsVertexTraitParserRuleCall_0_0());
            	            				
            	            pushFollow(FOLLOW_3);
            	            lv_traits_0_1=ruleVertexTrait();

            	            state._fsp--;


            	            					if (current==null) {
            	            						current = createModelElementForParent(grammarAccess.getModelRule());
            	            					}
            	            					add(
            	            						current,
            	            						"traits",
            	            						lv_traits_0_1,
            	            						"io.github.forsyde.io.Meta.VertexTrait");
            	            					afterParserOrEnumRuleCall();
            	            				

            	            }
            	            break;
            	        case 2 :
            	            // InternalMeta.g:98:5: lv_traits_0_2= ruleEdgeTrait
            	            {

            	            					newCompositeNode(grammarAccess.getModelAccess().getTraitsEdgeTraitParserRuleCall_0_1());
            	            				
            	            pushFollow(FOLLOW_3);
            	            lv_traits_0_2=ruleEdgeTrait();

            	            state._fsp--;


            	            					if (current==null) {
            	            						current = createModelElementForParent(grammarAccess.getModelRule());
            	            					}
            	            					add(
            	            						current,
            	            						"traits",
            	            						lv_traits_0_2,
            	            						"io.github.forsyde.io.Meta.EdgeTrait");
            	            					afterParserOrEnumRuleCall();
            	            				

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleVertexTrait"
    // InternalMeta.g:119:1: entryRuleVertexTrait returns [EObject current=null] : iv_ruleVertexTrait= ruleVertexTrait EOF ;
    public final EObject entryRuleVertexTrait() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTrait = null;


        try {
            // InternalMeta.g:119:52: (iv_ruleVertexTrait= ruleVertexTrait EOF )
            // InternalMeta.g:120:2: iv_ruleVertexTrait= ruleVertexTrait EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTrait=ruleVertexTrait();

            state._fsp--;

             current =iv_ruleVertexTrait; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTrait"


    // $ANTLR start "ruleVertexTrait"
    // InternalMeta.g:126:1: ruleVertexTrait returns [EObject current=null] : (otherlv_0= 'vertex' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '(' ( (lv_ports_7_0= ruleVertexTraitPort ) )* otherlv_8= ')' otherlv_9= '{' ( (lv_properties_10_0= ruleVertexTraitProperty ) )* otherlv_11= '}' ) ;
    public final EObject ruleVertexTrait() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        EObject lv_ports_7_0 = null;

        EObject lv_properties_10_0 = null;



        	enterRule();

        try {
            // InternalMeta.g:132:2: ( (otherlv_0= 'vertex' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '(' ( (lv_ports_7_0= ruleVertexTraitPort ) )* otherlv_8= ')' otherlv_9= '{' ( (lv_properties_10_0= ruleVertexTraitProperty ) )* otherlv_11= '}' ) )
            // InternalMeta.g:133:2: (otherlv_0= 'vertex' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '(' ( (lv_ports_7_0= ruleVertexTraitPort ) )* otherlv_8= ')' otherlv_9= '{' ( (lv_properties_10_0= ruleVertexTraitProperty ) )* otherlv_11= '}' )
            {
            // InternalMeta.g:133:2: (otherlv_0= 'vertex' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '(' ( (lv_ports_7_0= ruleVertexTraitPort ) )* otherlv_8= ')' otherlv_9= '{' ( (lv_properties_10_0= ruleVertexTraitProperty ) )* otherlv_11= '}' )
            // InternalMeta.g:134:3: otherlv_0= 'vertex' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '(' ( (lv_ports_7_0= ruleVertexTraitPort ) )* otherlv_8= ')' otherlv_9= '{' ( (lv_properties_10_0= ruleVertexTraitProperty ) )* otherlv_11= '}'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getVertexTraitAccess().getVertexKeyword_0());
            		
            // InternalMeta.g:138:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMeta.g:139:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMeta.g:139:4: (lv_name_1_0= RULE_ID )
            // InternalMeta.g:140:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_1_0, grammarAccess.getVertexTraitAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVertexTraitRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalMeta.g:156:3: (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==12) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalMeta.g:157:4: otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
                    {
                    otherlv_2=(Token)match(input,12,FOLLOW_4); 

                    				newLeafNode(otherlv_2, grammarAccess.getVertexTraitAccess().getRefinesKeyword_2_0());
                    			
                    // InternalMeta.g:161:4: ( (otherlv_3= RULE_ID ) )
                    // InternalMeta.g:162:5: (otherlv_3= RULE_ID )
                    {
                    // InternalMeta.g:162:5: (otherlv_3= RULE_ID )
                    // InternalMeta.g:163:6: otherlv_3= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVertexTraitRule());
                    						}
                    					
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_6); 

                    						newLeafNode(otherlv_3, grammarAccess.getVertexTraitAccess().getRefinesVertexTraitCrossReference_2_1_0());
                    					

                    }


                    }

                    // InternalMeta.g:174:4: (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==13) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalMeta.g:175:5: otherlv_4= ',' ( (otherlv_5= RULE_ID ) )
                    	    {
                    	    otherlv_4=(Token)match(input,13,FOLLOW_4); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getVertexTraitAccess().getCommaKeyword_2_2_0());
                    	    				
                    	    // InternalMeta.g:179:5: ( (otherlv_5= RULE_ID ) )
                    	    // InternalMeta.g:180:6: (otherlv_5= RULE_ID )
                    	    {
                    	    // InternalMeta.g:180:6: (otherlv_5= RULE_ID )
                    	    // InternalMeta.g:181:7: otherlv_5= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getVertexTraitRule());
                    	    							}
                    	    						
                    	    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_6); 

                    	    							newLeafNode(otherlv_5, grammarAccess.getVertexTraitAccess().getRefinesVertexTraitCrossReference_2_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,14,FOLLOW_7); 

            			newLeafNode(otherlv_6, grammarAccess.getVertexTraitAccess().getLeftParenthesisKeyword_3());
            		
            // InternalMeta.g:198:3: ( (lv_ports_7_0= ruleVertexTraitPort ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=34 && LA5_0<=36)||(LA5_0>=42 && LA5_0<=47)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalMeta.g:199:4: (lv_ports_7_0= ruleVertexTraitPort )
            	    {
            	    // InternalMeta.g:199:4: (lv_ports_7_0= ruleVertexTraitPort )
            	    // InternalMeta.g:200:5: lv_ports_7_0= ruleVertexTraitPort
            	    {

            	    					newCompositeNode(grammarAccess.getVertexTraitAccess().getPortsVertexTraitPortParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_ports_7_0=ruleVertexTraitPort();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getVertexTraitRule());
            	    					}
            	    					add(
            	    						current,
            	    						"ports",
            	    						lv_ports_7_0,
            	    						"io.github.forsyde.io.Meta.VertexTraitPort");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_8=(Token)match(input,15,FOLLOW_8); 

            			newLeafNode(otherlv_8, grammarAccess.getVertexTraitAccess().getRightParenthesisKeyword_5());
            		
            otherlv_9=(Token)match(input,16,FOLLOW_9); 

            			newLeafNode(otherlv_9, grammarAccess.getVertexTraitAccess().getLeftCurlyBracketKeyword_6());
            		
            // InternalMeta.g:225:3: ( (lv_properties_10_0= ruleVertexTraitProperty ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==18) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalMeta.g:226:4: (lv_properties_10_0= ruleVertexTraitProperty )
            	    {
            	    // InternalMeta.g:226:4: (lv_properties_10_0= ruleVertexTraitProperty )
            	    // InternalMeta.g:227:5: lv_properties_10_0= ruleVertexTraitProperty
            	    {

            	    					newCompositeNode(grammarAccess.getVertexTraitAccess().getPropertiesVertexTraitPropertyParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_9);
            	    lv_properties_10_0=ruleVertexTraitProperty();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getVertexTraitRule());
            	    					}
            	    					add(
            	    						current,
            	    						"properties",
            	    						lv_properties_10_0,
            	    						"io.github.forsyde.io.Meta.VertexTraitProperty");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            otherlv_11=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getVertexTraitAccess().getRightCurlyBracketKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTrait"


    // $ANTLR start "entryRuleVertexTraitProperty"
    // InternalMeta.g:252:1: entryRuleVertexTraitProperty returns [EObject current=null] : iv_ruleVertexTraitProperty= ruleVertexTraitProperty EOF ;
    public final EObject entryRuleVertexTraitProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTraitProperty = null;


        try {
            // InternalMeta.g:252:60: (iv_ruleVertexTraitProperty= ruleVertexTraitProperty EOF )
            // InternalMeta.g:253:2: iv_ruleVertexTraitProperty= ruleVertexTraitProperty EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTraitProperty=ruleVertexTraitProperty();

            state._fsp--;

             current =iv_ruleVertexTraitProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTraitProperty"


    // $ANTLR start "ruleVertexTraitProperty"
    // InternalMeta.g:259:1: ruleVertexTraitProperty returns [EObject current=null] : (otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleVertexTraitPropertyType ) ) ) ;
    public final EObject ruleVertexTraitProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        EObject lv_type_3_0 = null;



        	enterRule();

        try {
            // InternalMeta.g:265:2: ( (otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleVertexTraitPropertyType ) ) ) )
            // InternalMeta.g:266:2: (otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleVertexTraitPropertyType ) ) )
            {
            // InternalMeta.g:266:2: (otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleVertexTraitPropertyType ) ) )
            // InternalMeta.g:267:3: otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleVertexTraitPropertyType ) )
            {
            otherlv_0=(Token)match(input,18,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getVertexTraitPropertyAccess().getPropertyKeyword_0());
            		
            // InternalMeta.g:271:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMeta.g:272:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMeta.g:272:4: (lv_name_1_0= RULE_ID )
            // InternalMeta.g:273:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(lv_name_1_0, grammarAccess.getVertexTraitPropertyAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVertexTraitPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,19,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getVertexTraitPropertyAccess().getColonKeyword_2());
            		
            // InternalMeta.g:293:3: ( (lv_type_3_0= ruleVertexTraitPropertyType ) )
            // InternalMeta.g:294:4: (lv_type_3_0= ruleVertexTraitPropertyType )
            {
            // InternalMeta.g:294:4: (lv_type_3_0= ruleVertexTraitPropertyType )
            // InternalMeta.g:295:5: lv_type_3_0= ruleVertexTraitPropertyType
            {

            					newCompositeNode(grammarAccess.getVertexTraitPropertyAccess().getTypeVertexTraitPropertyTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_type_3_0=ruleVertexTraitPropertyType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVertexTraitPropertyRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_3_0,
            						"io.github.forsyde.io.Meta.VertexTraitPropertyType");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitProperty"


    // $ANTLR start "entryRuleVertexTraitPropertyType"
    // InternalMeta.g:316:1: entryRuleVertexTraitPropertyType returns [EObject current=null] : iv_ruleVertexTraitPropertyType= ruleVertexTraitPropertyType EOF ;
    public final EObject entryRuleVertexTraitPropertyType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTraitPropertyType = null;


        try {
            // InternalMeta.g:316:64: (iv_ruleVertexTraitPropertyType= ruleVertexTraitPropertyType EOF )
            // InternalMeta.g:317:2: iv_ruleVertexTraitPropertyType= ruleVertexTraitPropertyType EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitPropertyTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTraitPropertyType=ruleVertexTraitPropertyType();

            state._fsp--;

             current =iv_ruleVertexTraitPropertyType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTraitPropertyType"


    // $ANTLR start "ruleVertexTraitPropertyType"
    // InternalMeta.g:323:1: ruleVertexTraitPropertyType returns [EObject current=null] : (this_VertexTraitIntProperty_0= ruleVertexTraitIntProperty | this_VertexTraitRealProperty_1= ruleVertexTraitRealProperty | this_VertexTraitBoolProperty_2= ruleVertexTraitBoolProperty | this_VertexTraitStringProperty_3= ruleVertexTraitStringProperty | this_VertexTraitArrayProperty_4= ruleVertexTraitArrayProperty | this_VertexTraitObjectProperty_5= ruleVertexTraitObjectProperty ) ;
    public final EObject ruleVertexTraitPropertyType() throws RecognitionException {
        EObject current = null;

        EObject this_VertexTraitIntProperty_0 = null;

        EObject this_VertexTraitRealProperty_1 = null;

        EObject this_VertexTraitBoolProperty_2 = null;

        EObject this_VertexTraitStringProperty_3 = null;

        EObject this_VertexTraitArrayProperty_4 = null;

        EObject this_VertexTraitObjectProperty_5 = null;



        	enterRule();

        try {
            // InternalMeta.g:329:2: ( (this_VertexTraitIntProperty_0= ruleVertexTraitIntProperty | this_VertexTraitRealProperty_1= ruleVertexTraitRealProperty | this_VertexTraitBoolProperty_2= ruleVertexTraitBoolProperty | this_VertexTraitStringProperty_3= ruleVertexTraitStringProperty | this_VertexTraitArrayProperty_4= ruleVertexTraitArrayProperty | this_VertexTraitObjectProperty_5= ruleVertexTraitObjectProperty ) )
            // InternalMeta.g:330:2: (this_VertexTraitIntProperty_0= ruleVertexTraitIntProperty | this_VertexTraitRealProperty_1= ruleVertexTraitRealProperty | this_VertexTraitBoolProperty_2= ruleVertexTraitBoolProperty | this_VertexTraitStringProperty_3= ruleVertexTraitStringProperty | this_VertexTraitArrayProperty_4= ruleVertexTraitArrayProperty | this_VertexTraitObjectProperty_5= ruleVertexTraitObjectProperty )
            {
            // InternalMeta.g:330:2: (this_VertexTraitIntProperty_0= ruleVertexTraitIntProperty | this_VertexTraitRealProperty_1= ruleVertexTraitRealProperty | this_VertexTraitBoolProperty_2= ruleVertexTraitBoolProperty | this_VertexTraitStringProperty_3= ruleVertexTraitStringProperty | this_VertexTraitArrayProperty_4= ruleVertexTraitArrayProperty | this_VertexTraitObjectProperty_5= ruleVertexTraitObjectProperty )
            int alt7=6;
            switch ( input.LA(1) ) {
            case 20:
            case 21:
                {
                alt7=1;
                }
                break;
            case 23:
            case 24:
                {
                alt7=2;
                }
                break;
            case 25:
            case 26:
                {
                alt7=3;
                }
                break;
            case 28:
            case 29:
                {
                alt7=4;
                }
                break;
            case 30:
                {
                alt7=5;
                }
                break;
            case 33:
                {
                alt7=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalMeta.g:331:3: this_VertexTraitIntProperty_0= ruleVertexTraitIntProperty
                    {

                    			newCompositeNode(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitIntPropertyParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_VertexTraitIntProperty_0=ruleVertexTraitIntProperty();

                    state._fsp--;


                    			current = this_VertexTraitIntProperty_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMeta.g:340:3: this_VertexTraitRealProperty_1= ruleVertexTraitRealProperty
                    {

                    			newCompositeNode(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitRealPropertyParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_VertexTraitRealProperty_1=ruleVertexTraitRealProperty();

                    state._fsp--;


                    			current = this_VertexTraitRealProperty_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalMeta.g:349:3: this_VertexTraitBoolProperty_2= ruleVertexTraitBoolProperty
                    {

                    			newCompositeNode(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitBoolPropertyParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_VertexTraitBoolProperty_2=ruleVertexTraitBoolProperty();

                    state._fsp--;


                    			current = this_VertexTraitBoolProperty_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalMeta.g:358:3: this_VertexTraitStringProperty_3= ruleVertexTraitStringProperty
                    {

                    			newCompositeNode(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitStringPropertyParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_VertexTraitStringProperty_3=ruleVertexTraitStringProperty();

                    state._fsp--;


                    			current = this_VertexTraitStringProperty_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalMeta.g:367:3: this_VertexTraitArrayProperty_4= ruleVertexTraitArrayProperty
                    {

                    			newCompositeNode(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitArrayPropertyParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_VertexTraitArrayProperty_4=ruleVertexTraitArrayProperty();

                    state._fsp--;


                    			current = this_VertexTraitArrayProperty_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalMeta.g:376:3: this_VertexTraitObjectProperty_5= ruleVertexTraitObjectProperty
                    {

                    			newCompositeNode(grammarAccess.getVertexTraitPropertyTypeAccess().getVertexTraitObjectPropertyParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_VertexTraitObjectProperty_5=ruleVertexTraitObjectProperty();

                    state._fsp--;


                    			current = this_VertexTraitObjectProperty_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitPropertyType"


    // $ANTLR start "entryRuleVertexTraitIntProperty"
    // InternalMeta.g:388:1: entryRuleVertexTraitIntProperty returns [EObject current=null] : iv_ruleVertexTraitIntProperty= ruleVertexTraitIntProperty EOF ;
    public final EObject entryRuleVertexTraitIntProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTraitIntProperty = null;


        try {
            // InternalMeta.g:388:63: (iv_ruleVertexTraitIntProperty= ruleVertexTraitIntProperty EOF )
            // InternalMeta.g:389:2: iv_ruleVertexTraitIntProperty= ruleVertexTraitIntProperty EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitIntPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTraitIntProperty=ruleVertexTraitIntProperty();

            state._fsp--;

             current =iv_ruleVertexTraitIntProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTraitIntProperty"


    // $ANTLR start "ruleVertexTraitIntProperty"
    // InternalMeta.g:395:1: ruleVertexTraitIntProperty returns [EObject current=null] : ( (otherlv_0= 'int' | otherlv_1= 'integer' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_INT ) ) )? ) ;
    public final EObject ruleVertexTraitIntProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_default_3_0=null;


        	enterRule();

        try {
            // InternalMeta.g:401:2: ( ( (otherlv_0= 'int' | otherlv_1= 'integer' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_INT ) ) )? ) )
            // InternalMeta.g:402:2: ( (otherlv_0= 'int' | otherlv_1= 'integer' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_INT ) ) )? )
            {
            // InternalMeta.g:402:2: ( (otherlv_0= 'int' | otherlv_1= 'integer' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_INT ) ) )? )
            // InternalMeta.g:403:3: (otherlv_0= 'int' | otherlv_1= 'integer' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_INT ) ) )?
            {
            // InternalMeta.g:403:3: (otherlv_0= 'int' | otherlv_1= 'integer' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==20) ) {
                alt8=1;
            }
            else if ( (LA8_0==21) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalMeta.g:404:4: otherlv_0= 'int'
                    {
                    otherlv_0=(Token)match(input,20,FOLLOW_12); 

                    				newLeafNode(otherlv_0, grammarAccess.getVertexTraitIntPropertyAccess().getIntKeyword_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalMeta.g:409:4: otherlv_1= 'integer'
                    {
                    otherlv_1=(Token)match(input,21,FOLLOW_12); 

                    				newLeafNode(otherlv_1, grammarAccess.getVertexTraitIntPropertyAccess().getIntegerKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalMeta.g:414:3: (otherlv_2= '=' ( (lv_default_3_0= RULE_INT ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==22) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalMeta.g:415:4: otherlv_2= '=' ( (lv_default_3_0= RULE_INT ) )
                    {
                    otherlv_2=(Token)match(input,22,FOLLOW_13); 

                    				newLeafNode(otherlv_2, grammarAccess.getVertexTraitIntPropertyAccess().getEqualsSignKeyword_1_0());
                    			
                    // InternalMeta.g:419:4: ( (lv_default_3_0= RULE_INT ) )
                    // InternalMeta.g:420:5: (lv_default_3_0= RULE_INT )
                    {
                    // InternalMeta.g:420:5: (lv_default_3_0= RULE_INT )
                    // InternalMeta.g:421:6: lv_default_3_0= RULE_INT
                    {
                    lv_default_3_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    						newLeafNode(lv_default_3_0, grammarAccess.getVertexTraitIntPropertyAccess().getDefaultINTTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVertexTraitIntPropertyRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"default",
                    							lv_default_3_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitIntProperty"


    // $ANTLR start "entryRuleVertexTraitRealProperty"
    // InternalMeta.g:442:1: entryRuleVertexTraitRealProperty returns [EObject current=null] : iv_ruleVertexTraitRealProperty= ruleVertexTraitRealProperty EOF ;
    public final EObject entryRuleVertexTraitRealProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTraitRealProperty = null;


        try {
            // InternalMeta.g:442:64: (iv_ruleVertexTraitRealProperty= ruleVertexTraitRealProperty EOF )
            // InternalMeta.g:443:2: iv_ruleVertexTraitRealProperty= ruleVertexTraitRealProperty EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitRealPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTraitRealProperty=ruleVertexTraitRealProperty();

            state._fsp--;

             current =iv_ruleVertexTraitRealProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTraitRealProperty"


    // $ANTLR start "ruleVertexTraitRealProperty"
    // InternalMeta.g:449:1: ruleVertexTraitRealProperty returns [EObject current=null] : ( (otherlv_0= 'float' | ( (lv_double_1_0= 'double' ) ) ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleVertexTraitRealProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_double_1_0=null;
        Token otherlv_2=null;
        Token lv_default_3_0=null;


        	enterRule();

        try {
            // InternalMeta.g:455:2: ( ( (otherlv_0= 'float' | ( (lv_double_1_0= 'double' ) ) ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )? ) )
            // InternalMeta.g:456:2: ( (otherlv_0= 'float' | ( (lv_double_1_0= 'double' ) ) ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )? )
            {
            // InternalMeta.g:456:2: ( (otherlv_0= 'float' | ( (lv_double_1_0= 'double' ) ) ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )? )
            // InternalMeta.g:457:3: (otherlv_0= 'float' | ( (lv_double_1_0= 'double' ) ) ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )?
            {
            // InternalMeta.g:457:3: (otherlv_0= 'float' | ( (lv_double_1_0= 'double' ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            else if ( (LA10_0==24) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalMeta.g:458:4: otherlv_0= 'float'
                    {
                    otherlv_0=(Token)match(input,23,FOLLOW_12); 

                    				newLeafNode(otherlv_0, grammarAccess.getVertexTraitRealPropertyAccess().getFloatKeyword_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalMeta.g:463:4: ( (lv_double_1_0= 'double' ) )
                    {
                    // InternalMeta.g:463:4: ( (lv_double_1_0= 'double' ) )
                    // InternalMeta.g:464:5: (lv_double_1_0= 'double' )
                    {
                    // InternalMeta.g:464:5: (lv_double_1_0= 'double' )
                    // InternalMeta.g:465:6: lv_double_1_0= 'double'
                    {
                    lv_double_1_0=(Token)match(input,24,FOLLOW_12); 

                    						newLeafNode(lv_double_1_0, grammarAccess.getVertexTraitRealPropertyAccess().getDoubleDoubleKeyword_0_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVertexTraitRealPropertyRule());
                    						}
                    						setWithLastConsumed(current, "double", lv_double_1_0 != null, "double");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalMeta.g:478:3: (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==22) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalMeta.g:479:4: otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) )
                    {
                    otherlv_2=(Token)match(input,22,FOLLOW_14); 

                    				newLeafNode(otherlv_2, grammarAccess.getVertexTraitRealPropertyAccess().getEqualsSignKeyword_1_0());
                    			
                    // InternalMeta.g:483:4: ( (lv_default_3_0= RULE_STRING ) )
                    // InternalMeta.g:484:5: (lv_default_3_0= RULE_STRING )
                    {
                    // InternalMeta.g:484:5: (lv_default_3_0= RULE_STRING )
                    // InternalMeta.g:485:6: lv_default_3_0= RULE_STRING
                    {
                    lv_default_3_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_default_3_0, grammarAccess.getVertexTraitRealPropertyAccess().getDefaultSTRINGTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVertexTraitRealPropertyRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"default",
                    							lv_default_3_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitRealProperty"


    // $ANTLR start "entryRuleVertexTraitBoolProperty"
    // InternalMeta.g:506:1: entryRuleVertexTraitBoolProperty returns [EObject current=null] : iv_ruleVertexTraitBoolProperty= ruleVertexTraitBoolProperty EOF ;
    public final EObject entryRuleVertexTraitBoolProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTraitBoolProperty = null;


        try {
            // InternalMeta.g:506:64: (iv_ruleVertexTraitBoolProperty= ruleVertexTraitBoolProperty EOF )
            // InternalMeta.g:507:2: iv_ruleVertexTraitBoolProperty= ruleVertexTraitBoolProperty EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitBoolPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTraitBoolProperty=ruleVertexTraitBoolProperty();

            state._fsp--;

             current =iv_ruleVertexTraitBoolProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTraitBoolProperty"


    // $ANTLR start "ruleVertexTraitBoolProperty"
    // InternalMeta.g:513:1: ruleVertexTraitBoolProperty returns [EObject current=null] : ( (otherlv_0= 'bool' | otherlv_1= 'boolean' ) (otherlv_2= '=' ( (lv_default_3_0= 'true' ) ) )? ) ;
    public final EObject ruleVertexTraitBoolProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_default_3_0=null;


        	enterRule();

        try {
            // InternalMeta.g:519:2: ( ( (otherlv_0= 'bool' | otherlv_1= 'boolean' ) (otherlv_2= '=' ( (lv_default_3_0= 'true' ) ) )? ) )
            // InternalMeta.g:520:2: ( (otherlv_0= 'bool' | otherlv_1= 'boolean' ) (otherlv_2= '=' ( (lv_default_3_0= 'true' ) ) )? )
            {
            // InternalMeta.g:520:2: ( (otherlv_0= 'bool' | otherlv_1= 'boolean' ) (otherlv_2= '=' ( (lv_default_3_0= 'true' ) ) )? )
            // InternalMeta.g:521:3: (otherlv_0= 'bool' | otherlv_1= 'boolean' ) (otherlv_2= '=' ( (lv_default_3_0= 'true' ) ) )?
            {
            // InternalMeta.g:521:3: (otherlv_0= 'bool' | otherlv_1= 'boolean' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==25) ) {
                alt12=1;
            }
            else if ( (LA12_0==26) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalMeta.g:522:4: otherlv_0= 'bool'
                    {
                    otherlv_0=(Token)match(input,25,FOLLOW_12); 

                    				newLeafNode(otherlv_0, grammarAccess.getVertexTraitBoolPropertyAccess().getBoolKeyword_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalMeta.g:527:4: otherlv_1= 'boolean'
                    {
                    otherlv_1=(Token)match(input,26,FOLLOW_12); 

                    				newLeafNode(otherlv_1, grammarAccess.getVertexTraitBoolPropertyAccess().getBooleanKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalMeta.g:532:3: (otherlv_2= '=' ( (lv_default_3_0= 'true' ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==22) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalMeta.g:533:4: otherlv_2= '=' ( (lv_default_3_0= 'true' ) )
                    {
                    otherlv_2=(Token)match(input,22,FOLLOW_15); 

                    				newLeafNode(otherlv_2, grammarAccess.getVertexTraitBoolPropertyAccess().getEqualsSignKeyword_1_0());
                    			
                    // InternalMeta.g:537:4: ( (lv_default_3_0= 'true' ) )
                    // InternalMeta.g:538:5: (lv_default_3_0= 'true' )
                    {
                    // InternalMeta.g:538:5: (lv_default_3_0= 'true' )
                    // InternalMeta.g:539:6: lv_default_3_0= 'true'
                    {
                    lv_default_3_0=(Token)match(input,27,FOLLOW_2); 

                    						newLeafNode(lv_default_3_0, grammarAccess.getVertexTraitBoolPropertyAccess().getDefaultTrueKeyword_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVertexTraitBoolPropertyRule());
                    						}
                    						setWithLastConsumed(current, "default", lv_default_3_0 != null, "true");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitBoolProperty"


    // $ANTLR start "entryRuleVertexTraitStringProperty"
    // InternalMeta.g:556:1: entryRuleVertexTraitStringProperty returns [EObject current=null] : iv_ruleVertexTraitStringProperty= ruleVertexTraitStringProperty EOF ;
    public final EObject entryRuleVertexTraitStringProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTraitStringProperty = null;


        try {
            // InternalMeta.g:556:66: (iv_ruleVertexTraitStringProperty= ruleVertexTraitStringProperty EOF )
            // InternalMeta.g:557:2: iv_ruleVertexTraitStringProperty= ruleVertexTraitStringProperty EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitStringPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTraitStringProperty=ruleVertexTraitStringProperty();

            state._fsp--;

             current =iv_ruleVertexTraitStringProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTraitStringProperty"


    // $ANTLR start "ruleVertexTraitStringProperty"
    // InternalMeta.g:563:1: ruleVertexTraitStringProperty returns [EObject current=null] : ( (otherlv_0= 'string' | otherlv_1= 'str' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleVertexTraitStringProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_default_3_0=null;


        	enterRule();

        try {
            // InternalMeta.g:569:2: ( ( (otherlv_0= 'string' | otherlv_1= 'str' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )? ) )
            // InternalMeta.g:570:2: ( (otherlv_0= 'string' | otherlv_1= 'str' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )? )
            {
            // InternalMeta.g:570:2: ( (otherlv_0= 'string' | otherlv_1= 'str' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )? )
            // InternalMeta.g:571:3: (otherlv_0= 'string' | otherlv_1= 'str' ) (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )?
            {
            // InternalMeta.g:571:3: (otherlv_0= 'string' | otherlv_1= 'str' )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==28) ) {
                alt14=1;
            }
            else if ( (LA14_0==29) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // InternalMeta.g:572:4: otherlv_0= 'string'
                    {
                    otherlv_0=(Token)match(input,28,FOLLOW_12); 

                    				newLeafNode(otherlv_0, grammarAccess.getVertexTraitStringPropertyAccess().getStringKeyword_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalMeta.g:577:4: otherlv_1= 'str'
                    {
                    otherlv_1=(Token)match(input,29,FOLLOW_12); 

                    				newLeafNode(otherlv_1, grammarAccess.getVertexTraitStringPropertyAccess().getStrKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalMeta.g:582:3: (otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==22) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalMeta.g:583:4: otherlv_2= '=' ( (lv_default_3_0= RULE_STRING ) )
                    {
                    otherlv_2=(Token)match(input,22,FOLLOW_14); 

                    				newLeafNode(otherlv_2, grammarAccess.getVertexTraitStringPropertyAccess().getEqualsSignKeyword_1_0());
                    			
                    // InternalMeta.g:587:4: ( (lv_default_3_0= RULE_STRING ) )
                    // InternalMeta.g:588:5: (lv_default_3_0= RULE_STRING )
                    {
                    // InternalMeta.g:588:5: (lv_default_3_0= RULE_STRING )
                    // InternalMeta.g:589:6: lv_default_3_0= RULE_STRING
                    {
                    lv_default_3_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_default_3_0, grammarAccess.getVertexTraitStringPropertyAccess().getDefaultSTRINGTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVertexTraitStringPropertyRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"default",
                    							lv_default_3_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitStringProperty"


    // $ANTLR start "entryRuleVertexTraitArrayProperty"
    // InternalMeta.g:610:1: entryRuleVertexTraitArrayProperty returns [EObject current=null] : iv_ruleVertexTraitArrayProperty= ruleVertexTraitArrayProperty EOF ;
    public final EObject entryRuleVertexTraitArrayProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTraitArrayProperty = null;


        try {
            // InternalMeta.g:610:65: (iv_ruleVertexTraitArrayProperty= ruleVertexTraitArrayProperty EOF )
            // InternalMeta.g:611:2: iv_ruleVertexTraitArrayProperty= ruleVertexTraitArrayProperty EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitArrayPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTraitArrayProperty=ruleVertexTraitArrayProperty();

            state._fsp--;

             current =iv_ruleVertexTraitArrayProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTraitArrayProperty"


    // $ANTLR start "ruleVertexTraitArrayProperty"
    // InternalMeta.g:617:1: ruleVertexTraitArrayProperty returns [EObject current=null] : (otherlv_0= 'array' otherlv_1= '<' ( (lv_itemType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= '>' ) ;
    public final EObject ruleVertexTraitArrayProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_itemType_2_0 = null;



        	enterRule();

        try {
            // InternalMeta.g:623:2: ( (otherlv_0= 'array' otherlv_1= '<' ( (lv_itemType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= '>' ) )
            // InternalMeta.g:624:2: (otherlv_0= 'array' otherlv_1= '<' ( (lv_itemType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= '>' )
            {
            // InternalMeta.g:624:2: (otherlv_0= 'array' otherlv_1= '<' ( (lv_itemType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= '>' )
            // InternalMeta.g:625:3: otherlv_0= 'array' otherlv_1= '<' ( (lv_itemType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= '>'
            {
            otherlv_0=(Token)match(input,30,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getVertexTraitArrayPropertyAccess().getArrayKeyword_0());
            		
            otherlv_1=(Token)match(input,31,FOLLOW_11); 

            			newLeafNode(otherlv_1, grammarAccess.getVertexTraitArrayPropertyAccess().getLessThanSignKeyword_1());
            		
            // InternalMeta.g:633:3: ( (lv_itemType_2_0= ruleVertexTraitPropertyType ) )
            // InternalMeta.g:634:4: (lv_itemType_2_0= ruleVertexTraitPropertyType )
            {
            // InternalMeta.g:634:4: (lv_itemType_2_0= ruleVertexTraitPropertyType )
            // InternalMeta.g:635:5: lv_itemType_2_0= ruleVertexTraitPropertyType
            {

            					newCompositeNode(grammarAccess.getVertexTraitArrayPropertyAccess().getItemTypeVertexTraitPropertyTypeParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_17);
            lv_itemType_2_0=ruleVertexTraitPropertyType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVertexTraitArrayPropertyRule());
            					}
            					set(
            						current,
            						"itemType",
            						lv_itemType_2_0,
            						"io.github.forsyde.io.Meta.VertexTraitPropertyType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,32,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getVertexTraitArrayPropertyAccess().getGreaterThanSignKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitArrayProperty"


    // $ANTLR start "entryRuleVertexTraitObjectProperty"
    // InternalMeta.g:660:1: entryRuleVertexTraitObjectProperty returns [EObject current=null] : iv_ruleVertexTraitObjectProperty= ruleVertexTraitObjectProperty EOF ;
    public final EObject entryRuleVertexTraitObjectProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTraitObjectProperty = null;


        try {
            // InternalMeta.g:660:66: (iv_ruleVertexTraitObjectProperty= ruleVertexTraitObjectProperty EOF )
            // InternalMeta.g:661:2: iv_ruleVertexTraitObjectProperty= ruleVertexTraitObjectProperty EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitObjectPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTraitObjectProperty=ruleVertexTraitObjectProperty();

            state._fsp--;

             current =iv_ruleVertexTraitObjectProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTraitObjectProperty"


    // $ANTLR start "ruleVertexTraitObjectProperty"
    // InternalMeta.g:667:1: ruleVertexTraitObjectProperty returns [EObject current=null] : (otherlv_0= 'object' otherlv_1= '<' ( (lv_keyType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= ',' ( (lv_valueType_4_0= ruleVertexTraitPropertyType ) ) otherlv_5= '>' ) ;
    public final EObject ruleVertexTraitObjectProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_keyType_2_0 = null;

        EObject lv_valueType_4_0 = null;



        	enterRule();

        try {
            // InternalMeta.g:673:2: ( (otherlv_0= 'object' otherlv_1= '<' ( (lv_keyType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= ',' ( (lv_valueType_4_0= ruleVertexTraitPropertyType ) ) otherlv_5= '>' ) )
            // InternalMeta.g:674:2: (otherlv_0= 'object' otherlv_1= '<' ( (lv_keyType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= ',' ( (lv_valueType_4_0= ruleVertexTraitPropertyType ) ) otherlv_5= '>' )
            {
            // InternalMeta.g:674:2: (otherlv_0= 'object' otherlv_1= '<' ( (lv_keyType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= ',' ( (lv_valueType_4_0= ruleVertexTraitPropertyType ) ) otherlv_5= '>' )
            // InternalMeta.g:675:3: otherlv_0= 'object' otherlv_1= '<' ( (lv_keyType_2_0= ruleVertexTraitPropertyType ) ) otherlv_3= ',' ( (lv_valueType_4_0= ruleVertexTraitPropertyType ) ) otherlv_5= '>'
            {
            otherlv_0=(Token)match(input,33,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getVertexTraitObjectPropertyAccess().getObjectKeyword_0());
            		
            otherlv_1=(Token)match(input,31,FOLLOW_11); 

            			newLeafNode(otherlv_1, grammarAccess.getVertexTraitObjectPropertyAccess().getLessThanSignKeyword_1());
            		
            // InternalMeta.g:683:3: ( (lv_keyType_2_0= ruleVertexTraitPropertyType ) )
            // InternalMeta.g:684:4: (lv_keyType_2_0= ruleVertexTraitPropertyType )
            {
            // InternalMeta.g:684:4: (lv_keyType_2_0= ruleVertexTraitPropertyType )
            // InternalMeta.g:685:5: lv_keyType_2_0= ruleVertexTraitPropertyType
            {

            					newCompositeNode(grammarAccess.getVertexTraitObjectPropertyAccess().getKeyTypeVertexTraitPropertyTypeParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_18);
            lv_keyType_2_0=ruleVertexTraitPropertyType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVertexTraitObjectPropertyRule());
            					}
            					set(
            						current,
            						"keyType",
            						lv_keyType_2_0,
            						"io.github.forsyde.io.Meta.VertexTraitPropertyType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_3, grammarAccess.getVertexTraitObjectPropertyAccess().getCommaKeyword_3());
            		
            // InternalMeta.g:706:3: ( (lv_valueType_4_0= ruleVertexTraitPropertyType ) )
            // InternalMeta.g:707:4: (lv_valueType_4_0= ruleVertexTraitPropertyType )
            {
            // InternalMeta.g:707:4: (lv_valueType_4_0= ruleVertexTraitPropertyType )
            // InternalMeta.g:708:5: lv_valueType_4_0= ruleVertexTraitPropertyType
            {

            					newCompositeNode(grammarAccess.getVertexTraitObjectPropertyAccess().getValueTypeVertexTraitPropertyTypeParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_17);
            lv_valueType_4_0=ruleVertexTraitPropertyType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVertexTraitObjectPropertyRule());
            					}
            					set(
            						current,
            						"valueType",
            						lv_valueType_4_0,
            						"io.github.forsyde.io.Meta.VertexTraitPropertyType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getVertexTraitObjectPropertyAccess().getGreaterThanSignKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitObjectProperty"


    // $ANTLR start "entryRuleVertexTraitPort"
    // InternalMeta.g:733:1: entryRuleVertexTraitPort returns [EObject current=null] : iv_ruleVertexTraitPort= ruleVertexTraitPort EOF ;
    public final EObject entryRuleVertexTraitPort() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertexTraitPort = null;


        try {
            // InternalMeta.g:733:56: (iv_ruleVertexTraitPort= ruleVertexTraitPort EOF )
            // InternalMeta.g:734:2: iv_ruleVertexTraitPort= ruleVertexTraitPort EOF
            {
             newCompositeNode(grammarAccess.getVertexTraitPortRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertexTraitPort=ruleVertexTraitPort();

            state._fsp--;

             current =iv_ruleVertexTraitPort; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertexTraitPort"


    // $ANTLR start "ruleVertexTraitPort"
    // InternalMeta.g:740:1: ruleVertexTraitPort returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleVertexTraitPort() throws RecognitionException {
        EObject current = null;

        Token lv_multiple_1_0=null;
        Token lv_ordered_2_0=null;
        Token otherlv_4=null;
        Token lv_name_5_0=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Enumerator lv_direction_3_0 = null;



        	enterRule();

        try {
            // InternalMeta.g:746:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) )
            // InternalMeta.g:747:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+ {...}?) ) )
            {
            // InternalMeta.g:747:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+ {...}?) ) )
            // InternalMeta.g:748:3: ( ( ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+ {...}?) )
            {
            // InternalMeta.g:748:3: ( ( ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+ {...}?) )
            // InternalMeta.g:749:4: ( ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup());
            			
            // InternalMeta.g:752:4: ( ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+ {...}?)
            // InternalMeta.g:753:5: ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+ {...}?
            {
            // InternalMeta.g:753:5: ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+
            int cnt17=0;
            loop17:
            do {
                int alt17=4;
                alt17 = dfa17.predict(input);
                switch (alt17) {
            	case 1 :
            	    // InternalMeta.g:754:3: ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) )
            	    {
            	    // InternalMeta.g:754:3: ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) )
            	    // InternalMeta.g:755:4: {...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleVertexTraitPort", "getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalMeta.g:755:109: ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) )
            	    // InternalMeta.g:756:5: ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalMeta.g:759:8: ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) )
            	    // InternalMeta.g:759:9: {...}? => ( (lv_multiple_1_0= 'multiple' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleVertexTraitPort", "true");
            	    }
            	    // InternalMeta.g:759:18: ( (lv_multiple_1_0= 'multiple' ) )
            	    // InternalMeta.g:759:19: (lv_multiple_1_0= 'multiple' )
            	    {
            	    // InternalMeta.g:759:19: (lv_multiple_1_0= 'multiple' )
            	    // InternalMeta.g:760:9: lv_multiple_1_0= 'multiple'
            	    {
            	    lv_multiple_1_0=(Token)match(input,34,FOLLOW_19); 

            	    									newLeafNode(lv_multiple_1_0, grammarAccess.getVertexTraitPortAccess().getMultipleMultipleKeyword_0_0());
            	    								

            	    									if (current==null) {
            	    										current = createModelElement(grammarAccess.getVertexTraitPortRule());
            	    									}
            	    									setWithLastConsumed(current, "multiple", lv_multiple_1_0 != null, "multiple");
            	    								

            	    }


            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalMeta.g:777:3: ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) )
            	    {
            	    // InternalMeta.g:777:3: ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) )
            	    // InternalMeta.g:778:4: {...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleVertexTraitPort", "getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalMeta.g:778:109: ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) )
            	    // InternalMeta.g:779:5: ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalMeta.g:782:8: ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) )
            	    // InternalMeta.g:782:9: {...}? => ( (lv_ordered_2_0= 'ordered' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleVertexTraitPort", "true");
            	    }
            	    // InternalMeta.g:782:18: ( (lv_ordered_2_0= 'ordered' ) )
            	    // InternalMeta.g:782:19: (lv_ordered_2_0= 'ordered' )
            	    {
            	    // InternalMeta.g:782:19: (lv_ordered_2_0= 'ordered' )
            	    // InternalMeta.g:783:9: lv_ordered_2_0= 'ordered'
            	    {
            	    lv_ordered_2_0=(Token)match(input,35,FOLLOW_19); 

            	    									newLeafNode(lv_ordered_2_0, grammarAccess.getVertexTraitPortAccess().getOrderedOrderedKeyword_1_0());
            	    								

            	    									if (current==null) {
            	    										current = createModelElement(grammarAccess.getVertexTraitPortRule());
            	    									}
            	    									setWithLastConsumed(current, "ordered", lv_ordered_2_0 != null, "ordered");
            	    								

            	    }


            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalMeta.g:800:3: ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) )
            	    {
            	    // InternalMeta.g:800:3: ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) )
            	    // InternalMeta.g:801:4: {...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleVertexTraitPort", "getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalMeta.g:801:109: ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) )
            	    // InternalMeta.g:802:5: ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalMeta.g:805:8: ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) )
            	    // InternalMeta.g:805:9: {...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleVertexTraitPort", "true");
            	    }
            	    // InternalMeta.g:805:18: ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) )
            	    // InternalMeta.g:805:19: ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) )
            	    {
            	    // InternalMeta.g:805:19: ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )?
            	    int alt16=2;
            	    int LA16_0 = input.LA(1);

            	    if ( ((LA16_0>=42 && LA16_0<=47)) ) {
            	        alt16=1;
            	    }
            	    switch (alt16) {
            	        case 1 :
            	            // InternalMeta.g:806:9: (lv_direction_3_0= ruleVertexTraitPortDirection )
            	            {
            	            // InternalMeta.g:806:9: (lv_direction_3_0= ruleVertexTraitPortDirection )
            	            // InternalMeta.g:807:10: lv_direction_3_0= ruleVertexTraitPortDirection
            	            {

            	            										newCompositeNode(grammarAccess.getVertexTraitPortAccess().getDirectionVertexTraitPortDirectionEnumRuleCall_2_0_0());
            	            									
            	            pushFollow(FOLLOW_20);
            	            lv_direction_3_0=ruleVertexTraitPortDirection();

            	            state._fsp--;


            	            										if (current==null) {
            	            											current = createModelElementForParent(grammarAccess.getVertexTraitPortRule());
            	            										}
            	            										set(
            	            											current,
            	            											"direction",
            	            											lv_direction_3_0,
            	            											"io.github.forsyde.io.Meta.VertexTraitPortDirection");
            	            										afterParserOrEnumRuleCall();
            	            									

            	            }


            	            }
            	            break;

            	    }

            	    otherlv_4=(Token)match(input,36,FOLLOW_4); 

            	    								newLeafNode(otherlv_4, grammarAccess.getVertexTraitPortAccess().getPortKeyword_2_1());
            	    							
            	    // InternalMeta.g:828:8: ( (lv_name_5_0= RULE_ID ) )
            	    // InternalMeta.g:829:9: (lv_name_5_0= RULE_ID )
            	    {
            	    // InternalMeta.g:829:9: (lv_name_5_0= RULE_ID )
            	    // InternalMeta.g:830:10: lv_name_5_0= RULE_ID
            	    {
            	    lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_10); 

            	    										newLeafNode(lv_name_5_0, grammarAccess.getVertexTraitPortAccess().getNameIDTerminalRuleCall_2_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getVertexTraitPortRule());
            	    										}
            	    										setWithLastConsumed(
            	    											current,
            	    											"name",
            	    											lv_name_5_0,
            	    											"org.eclipse.xtext.common.Terminals.ID");
            	    									

            	    }


            	    }

            	    otherlv_6=(Token)match(input,19,FOLLOW_4); 

            	    								newLeafNode(otherlv_6, grammarAccess.getVertexTraitPortAccess().getColonKeyword_2_3());
            	    							
            	    // InternalMeta.g:850:8: ( (otherlv_7= RULE_ID ) )
            	    // InternalMeta.g:851:9: (otherlv_7= RULE_ID )
            	    {
            	    // InternalMeta.g:851:9: (otherlv_7= RULE_ID )
            	    // InternalMeta.g:852:10: otherlv_7= RULE_ID
            	    {

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getVertexTraitPortRule());
            	    										}
            	    									
            	    otherlv_7=(Token)match(input,RULE_ID,FOLLOW_19); 

            	    										newLeafNode(otherlv_7, grammarAccess.getVertexTraitPortAccess().getTraitVertexTraitCrossReference_2_4_0());
            	    									

            	    }


            	    }


            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "ruleVertexTraitPort", "getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup())");
            }

            }


            }

             
            			  getUnorderedGroupHelper().leave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup());
            			

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitPort"


    // $ANTLR start "entryRuleEdgeTrait"
    // InternalMeta.g:880:1: entryRuleEdgeTrait returns [EObject current=null] : iv_ruleEdgeTrait= ruleEdgeTrait EOF ;
    public final EObject entryRuleEdgeTrait() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEdgeTrait = null;


        try {
            // InternalMeta.g:880:50: (iv_ruleEdgeTrait= ruleEdgeTrait EOF )
            // InternalMeta.g:881:2: iv_ruleEdgeTrait= ruleEdgeTrait EOF
            {
             newCompositeNode(grammarAccess.getEdgeTraitRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEdgeTrait=ruleEdgeTrait();

            state._fsp--;

             current =iv_ruleEdgeTrait; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEdgeTrait"


    // $ANTLR start "ruleEdgeTrait"
    // InternalMeta.g:887:1: ruleEdgeTrait returns [EObject current=null] : (otherlv_0= 'edge' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '{' ( (lv_specs_7_0= ruleEdgeTraitConstraint ) )* otherlv_8= '}' ) ;
    public final EObject ruleEdgeTrait() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_specs_7_0 = null;



        	enterRule();

        try {
            // InternalMeta.g:893:2: ( (otherlv_0= 'edge' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '{' ( (lv_specs_7_0= ruleEdgeTraitConstraint ) )* otherlv_8= '}' ) )
            // InternalMeta.g:894:2: (otherlv_0= 'edge' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '{' ( (lv_specs_7_0= ruleEdgeTraitConstraint ) )* otherlv_8= '}' )
            {
            // InternalMeta.g:894:2: (otherlv_0= 'edge' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '{' ( (lv_specs_7_0= ruleEdgeTraitConstraint ) )* otherlv_8= '}' )
            // InternalMeta.g:895:3: otherlv_0= 'edge' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '{' ( (lv_specs_7_0= ruleEdgeTraitConstraint ) )* otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,37,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getEdgeTraitAccess().getEdgeKeyword_0());
            		
            // InternalMeta.g:899:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMeta.g:900:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMeta.g:900:4: (lv_name_1_0= RULE_ID )
            // InternalMeta.g:901:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_21); 

            					newLeafNode(lv_name_1_0, grammarAccess.getEdgeTraitAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEdgeTraitRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalMeta.g:917:3: (otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==12) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalMeta.g:918:4: otherlv_2= 'refines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
                    {
                    otherlv_2=(Token)match(input,12,FOLLOW_4); 

                    				newLeafNode(otherlv_2, grammarAccess.getEdgeTraitAccess().getRefinesKeyword_2_0());
                    			
                    // InternalMeta.g:922:4: ( (otherlv_3= RULE_ID ) )
                    // InternalMeta.g:923:5: (otherlv_3= RULE_ID )
                    {
                    // InternalMeta.g:923:5: (otherlv_3= RULE_ID )
                    // InternalMeta.g:924:6: otherlv_3= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getEdgeTraitRule());
                    						}
                    					
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_22); 

                    						newLeafNode(otherlv_3, grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitCrossReference_2_1_0());
                    					

                    }


                    }

                    // InternalMeta.g:935:4: (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==13) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalMeta.g:936:5: otherlv_4= ',' ( (otherlv_5= RULE_ID ) )
                    	    {
                    	    otherlv_4=(Token)match(input,13,FOLLOW_4); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getEdgeTraitAccess().getCommaKeyword_2_2_0());
                    	    				
                    	    // InternalMeta.g:940:5: ( (otherlv_5= RULE_ID ) )
                    	    // InternalMeta.g:941:6: (otherlv_5= RULE_ID )
                    	    {
                    	    // InternalMeta.g:941:6: (otherlv_5= RULE_ID )
                    	    // InternalMeta.g:942:7: otherlv_5= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getEdgeTraitRule());
                    	    							}
                    	    						
                    	    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_22); 

                    	    							newLeafNode(otherlv_5, grammarAccess.getEdgeTraitAccess().getRefinesEdgeTraitCrossReference_2_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,16,FOLLOW_23); 

            			newLeafNode(otherlv_6, grammarAccess.getEdgeTraitAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalMeta.g:959:3: ( (lv_specs_7_0= ruleEdgeTraitConstraint ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==38) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalMeta.g:960:4: (lv_specs_7_0= ruleEdgeTraitConstraint )
            	    {
            	    // InternalMeta.g:960:4: (lv_specs_7_0= ruleEdgeTraitConstraint )
            	    // InternalMeta.g:961:5: lv_specs_7_0= ruleEdgeTraitConstraint
            	    {

            	    					newCompositeNode(grammarAccess.getEdgeTraitAccess().getSpecsEdgeTraitConstraintParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_23);
            	    lv_specs_7_0=ruleEdgeTraitConstraint();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getEdgeTraitRule());
            	    					}
            	    					add(
            	    						current,
            	    						"specs",
            	    						lv_specs_7_0,
            	    						"io.github.forsyde.io.Meta.EdgeTraitConstraint");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            otherlv_8=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getEdgeTraitAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEdgeTrait"


    // $ANTLR start "entryRuleEdgeTraitConstraint"
    // InternalMeta.g:986:1: entryRuleEdgeTraitConstraint returns [EObject current=null] : iv_ruleEdgeTraitConstraint= ruleEdgeTraitConstraint EOF ;
    public final EObject entryRuleEdgeTraitConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEdgeTraitConstraint = null;


        try {
            // InternalMeta.g:986:60: (iv_ruleEdgeTraitConstraint= ruleEdgeTraitConstraint EOF )
            // InternalMeta.g:987:2: iv_ruleEdgeTraitConstraint= ruleEdgeTraitConstraint EOF
            {
             newCompositeNode(grammarAccess.getEdgeTraitConstraintRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEdgeTraitConstraint=ruleEdgeTraitConstraint();

            state._fsp--;

             current =iv_ruleEdgeTraitConstraint; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEdgeTraitConstraint"


    // $ANTLR start "ruleEdgeTraitConstraint"
    // InternalMeta.g:993:1: ruleEdgeTraitConstraint returns [EObject current=null] : (otherlv_0= 'constraint' ( ( (lv_target_1_0= 'target' ) ) | ( (lv_source_2_0= 'source' ) ) ) ( (otherlv_3= RULE_ID ) ) (otherlv_4= 'or' ( (otherlv_5= RULE_ID ) ) )* ) ;
    public final EObject ruleEdgeTraitConstraint() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_target_1_0=null;
        Token lv_source_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalMeta.g:999:2: ( (otherlv_0= 'constraint' ( ( (lv_target_1_0= 'target' ) ) | ( (lv_source_2_0= 'source' ) ) ) ( (otherlv_3= RULE_ID ) ) (otherlv_4= 'or' ( (otherlv_5= RULE_ID ) ) )* ) )
            // InternalMeta.g:1000:2: (otherlv_0= 'constraint' ( ( (lv_target_1_0= 'target' ) ) | ( (lv_source_2_0= 'source' ) ) ) ( (otherlv_3= RULE_ID ) ) (otherlv_4= 'or' ( (otherlv_5= RULE_ID ) ) )* )
            {
            // InternalMeta.g:1000:2: (otherlv_0= 'constraint' ( ( (lv_target_1_0= 'target' ) ) | ( (lv_source_2_0= 'source' ) ) ) ( (otherlv_3= RULE_ID ) ) (otherlv_4= 'or' ( (otherlv_5= RULE_ID ) ) )* )
            // InternalMeta.g:1001:3: otherlv_0= 'constraint' ( ( (lv_target_1_0= 'target' ) ) | ( (lv_source_2_0= 'source' ) ) ) ( (otherlv_3= RULE_ID ) ) (otherlv_4= 'or' ( (otherlv_5= RULE_ID ) ) )*
            {
            otherlv_0=(Token)match(input,38,FOLLOW_24); 

            			newLeafNode(otherlv_0, grammarAccess.getEdgeTraitConstraintAccess().getConstraintKeyword_0());
            		
            // InternalMeta.g:1005:3: ( ( (lv_target_1_0= 'target' ) ) | ( (lv_source_2_0= 'source' ) ) )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==39) ) {
                alt21=1;
            }
            else if ( (LA21_0==40) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // InternalMeta.g:1006:4: ( (lv_target_1_0= 'target' ) )
                    {
                    // InternalMeta.g:1006:4: ( (lv_target_1_0= 'target' ) )
                    // InternalMeta.g:1007:5: (lv_target_1_0= 'target' )
                    {
                    // InternalMeta.g:1007:5: (lv_target_1_0= 'target' )
                    // InternalMeta.g:1008:6: lv_target_1_0= 'target'
                    {
                    lv_target_1_0=(Token)match(input,39,FOLLOW_4); 

                    						newLeafNode(lv_target_1_0, grammarAccess.getEdgeTraitConstraintAccess().getTargetTargetKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getEdgeTraitConstraintRule());
                    						}
                    						setWithLastConsumed(current, "target", lv_target_1_0 != null, "target");
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:1021:4: ( (lv_source_2_0= 'source' ) )
                    {
                    // InternalMeta.g:1021:4: ( (lv_source_2_0= 'source' ) )
                    // InternalMeta.g:1022:5: (lv_source_2_0= 'source' )
                    {
                    // InternalMeta.g:1022:5: (lv_source_2_0= 'source' )
                    // InternalMeta.g:1023:6: lv_source_2_0= 'source'
                    {
                    lv_source_2_0=(Token)match(input,40,FOLLOW_4); 

                    						newLeafNode(lv_source_2_0, grammarAccess.getEdgeTraitConstraintAccess().getSourceSourceKeyword_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getEdgeTraitConstraintRule());
                    						}
                    						setWithLastConsumed(current, "source", lv_source_2_0 != null, "source");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalMeta.g:1036:3: ( (otherlv_3= RULE_ID ) )
            // InternalMeta.g:1037:4: (otherlv_3= RULE_ID )
            {
            // InternalMeta.g:1037:4: (otherlv_3= RULE_ID )
            // InternalMeta.g:1038:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEdgeTraitConstraintRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_25); 

            					newLeafNode(otherlv_3, grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitCrossReference_2_0());
            				

            }


            }

            // InternalMeta.g:1049:3: (otherlv_4= 'or' ( (otherlv_5= RULE_ID ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==41) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalMeta.g:1050:4: otherlv_4= 'or' ( (otherlv_5= RULE_ID ) )
            	    {
            	    otherlv_4=(Token)match(input,41,FOLLOW_4); 

            	    				newLeafNode(otherlv_4, grammarAccess.getEdgeTraitConstraintAccess().getOrKeyword_3_0());
            	    			
            	    // InternalMeta.g:1054:4: ( (otherlv_5= RULE_ID ) )
            	    // InternalMeta.g:1055:5: (otherlv_5= RULE_ID )
            	    {
            	    // InternalMeta.g:1055:5: (otherlv_5= RULE_ID )
            	    // InternalMeta.g:1056:6: otherlv_5= RULE_ID
            	    {

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getEdgeTraitConstraintRule());
            	    						}
            	    					
            	    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_25); 

            	    						newLeafNode(otherlv_5, grammarAccess.getEdgeTraitConstraintAccess().getFilterVertexTraitCrossReference_3_1_0());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEdgeTraitConstraint"


    // $ANTLR start "ruleVertexTraitPortDirection"
    // InternalMeta.g:1072:1: ruleVertexTraitPortDirection returns [Enumerator current=null] : ( (enumLiteral_0= 'inout' ) | (enumLiteral_1= 'outin' ) | (enumLiteral_2= 'out' ) | (enumLiteral_3= 'outgoing' ) | (enumLiteral_4= 'in' ) | (enumLiteral_5= 'incoming' ) ) ;
    public final Enumerator ruleVertexTraitPortDirection() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalMeta.g:1078:2: ( ( (enumLiteral_0= 'inout' ) | (enumLiteral_1= 'outin' ) | (enumLiteral_2= 'out' ) | (enumLiteral_3= 'outgoing' ) | (enumLiteral_4= 'in' ) | (enumLiteral_5= 'incoming' ) ) )
            // InternalMeta.g:1079:2: ( (enumLiteral_0= 'inout' ) | (enumLiteral_1= 'outin' ) | (enumLiteral_2= 'out' ) | (enumLiteral_3= 'outgoing' ) | (enumLiteral_4= 'in' ) | (enumLiteral_5= 'incoming' ) )
            {
            // InternalMeta.g:1079:2: ( (enumLiteral_0= 'inout' ) | (enumLiteral_1= 'outin' ) | (enumLiteral_2= 'out' ) | (enumLiteral_3= 'outgoing' ) | (enumLiteral_4= 'in' ) | (enumLiteral_5= 'incoming' ) )
            int alt23=6;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt23=1;
                }
                break;
            case 43:
                {
                alt23=2;
                }
                break;
            case 44:
                {
                alt23=3;
                }
                break;
            case 45:
                {
                alt23=4;
                }
                break;
            case 46:
                {
                alt23=5;
                }
                break;
            case 47:
                {
                alt23=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // InternalMeta.g:1080:3: (enumLiteral_0= 'inout' )
                    {
                    // InternalMeta.g:1080:3: (enumLiteral_0= 'inout' )
                    // InternalMeta.g:1081:4: enumLiteral_0= 'inout'
                    {
                    enumLiteral_0=(Token)match(input,42,FOLLOW_2); 

                    				current = grammarAccess.getVertexTraitPortDirectionAccess().getBIDIRECTIONALEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getVertexTraitPortDirectionAccess().getBIDIRECTIONALEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalMeta.g:1088:3: (enumLiteral_1= 'outin' )
                    {
                    // InternalMeta.g:1088:3: (enumLiteral_1= 'outin' )
                    // InternalMeta.g:1089:4: enumLiteral_1= 'outin'
                    {
                    enumLiteral_1=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getVertexTraitPortDirectionAccess().getBIDIRECTIONALEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getVertexTraitPortDirectionAccess().getBIDIRECTIONALEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalMeta.g:1096:3: (enumLiteral_2= 'out' )
                    {
                    // InternalMeta.g:1096:3: (enumLiteral_2= 'out' )
                    // InternalMeta.g:1097:4: enumLiteral_2= 'out'
                    {
                    enumLiteral_2=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getVertexTraitPortDirectionAccess().getOUTGOINGEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getVertexTraitPortDirectionAccess().getOUTGOINGEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalMeta.g:1104:3: (enumLiteral_3= 'outgoing' )
                    {
                    // InternalMeta.g:1104:3: (enumLiteral_3= 'outgoing' )
                    // InternalMeta.g:1105:4: enumLiteral_3= 'outgoing'
                    {
                    enumLiteral_3=(Token)match(input,45,FOLLOW_2); 

                    				current = grammarAccess.getVertexTraitPortDirectionAccess().getOUTGOINGEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getVertexTraitPortDirectionAccess().getOUTGOINGEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalMeta.g:1112:3: (enumLiteral_4= 'in' )
                    {
                    // InternalMeta.g:1112:3: (enumLiteral_4= 'in' )
                    // InternalMeta.g:1113:4: enumLiteral_4= 'in'
                    {
                    enumLiteral_4=(Token)match(input,46,FOLLOW_2); 

                    				current = grammarAccess.getVertexTraitPortDirectionAccess().getINCOMINGEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getVertexTraitPortDirectionAccess().getINCOMINGEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalMeta.g:1120:3: (enumLiteral_5= 'incoming' )
                    {
                    // InternalMeta.g:1120:3: (enumLiteral_5= 'incoming' )
                    // InternalMeta.g:1121:4: enumLiteral_5= 'incoming'
                    {
                    enumLiteral_5=(Token)match(input,47,FOLLOW_2); 

                    				current = grammarAccess.getVertexTraitPortDirectionAccess().getINCOMINGEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getVertexTraitPortDirectionAccess().getINCOMINGEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertexTraitPortDirection"

    // Delegated rules


    protected DFA17 dfa17 = new DFA17(this);
    static final String dfa_1s = "\16\uffff";
    static final String dfa_2s = "\1\1\15\uffff";
    static final String dfa_3s = "\1\17\1\uffff\11\0\3\uffff";
    static final String dfa_4s = "\1\57\1\uffff\11\0\3\uffff";
    static final String dfa_5s = "\1\uffff\1\4\11\uffff\1\1\1\2\1\3";
    static final String dfa_6s = "\2\uffff\1\0\1\4\1\2\1\10\1\5\1\1\1\7\1\3\1\6\3\uffff}>";
    static final String[] dfa_7s = {
            "\1\1\22\uffff\1\2\1\3\1\12\5\uffff\1\4\1\5\1\6\1\7\1\10\1\11",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "()+ loopback of 753:5: ( ({...}? => ( ({...}? => ( (lv_multiple_1_0= 'multiple' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_ordered_2_0= 'ordered' ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_direction_3_0= ruleVertexTraitPortDirection ) )? otherlv_4= 'port' ( (lv_name_5_0= RULE_ID ) ) otherlv_6= ':' ( (otherlv_7= RULE_ID ) ) ) ) ) ) )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA17_2 = input.LA(1);

                         
                        int index17_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 0) ) {s = 11;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 1;}

                         
                        input.seek(index17_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA17_7 = input.LA(1);

                         
                        int index17_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {s = 13;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 1;}

                         
                        input.seek(index17_7);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA17_4 = input.LA(1);

                         
                        int index17_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {s = 13;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 1;}

                         
                        input.seek(index17_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA17_9 = input.LA(1);

                         
                        int index17_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {s = 13;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 1;}

                         
                        input.seek(index17_9);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA17_3 = input.LA(1);

                         
                        int index17_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 1) ) {s = 12;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 1;}

                         
                        input.seek(index17_3);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA17_6 = input.LA(1);

                         
                        int index17_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {s = 13;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 1;}

                         
                        input.seek(index17_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA17_10 = input.LA(1);

                         
                        int index17_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {s = 13;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 1;}

                         
                        input.seek(index17_10);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA17_8 = input.LA(1);

                         
                        int index17_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {s = 13;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 1;}

                         
                        input.seek(index17_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA17_5 = input.LA(1);

                         
                        int index17_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( getUnorderedGroupHelper().canSelect(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup(), 2) ) {s = 13;}

                        else if ( getUnorderedGroupHelper().canLeave(grammarAccess.getVertexTraitPortAccess().getUnorderedGroup()) ) {s = 1;}

                         
                        input.seek(index17_5);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 17, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000002000000802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000FC1C00008000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000277B00000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000FC1C00000002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000011000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000004000020000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000020000000002L});

}
