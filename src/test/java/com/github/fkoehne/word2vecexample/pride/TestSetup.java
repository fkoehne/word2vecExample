/**
 * 
 */
package com.github.fkoehne.word2vecexample.pride;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EndingPreProcessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSetup {

    private static Word2Vec vec = null;

    @BeforeClass
    public static void learn() throws IOException {
        final SentenceIterator iter = new LineSentenceIterator(
                new File(TestSetup.class.getResource("pg1342.txt").getFile()));
        iter.setPreProcessor(new SentencePreProcessor() {

            public String preProcess(final String sentence) {
                return sentence.toLowerCase();
            }
        });

        final EndingPreProcessor preProcessor = new EndingPreProcessor();
        final TokenizerFactory tokenizer = new DefaultTokenizerFactory();
        tokenizer.setTokenPreProcessor(new TokenPreProcess() {

            public String preProcess(final String token) {
                String base = preProcessor.preProcess(token.toLowerCase());
                // Replace special characters with nothing in one swipe
                final String[] patterns = { "_", ",", "\"", "'", ";", ";", "\\.", "!" };
                final String[] nothing = ArrayUtils.clone(patterns);
                for (int i = 0; i < patterns.length; i++) {
                    nothing[i] = "";
                }
                base = StringUtils.replaceEach(base, patterns, nothing);
                return base;
            }
        });

        final int batchSize = 1000;
        final int iterations = 8;
        final int layerSize = 120;

        vec = new Word2Vec.Builder().batchSize(batchSize) // # words per minibatch.
                .minWordFrequency(5) //
                .useAdaGrad(false) //
                .layerSize(layerSize) // word feature vector size
                .iterations(iterations) // # iterations to train
                .learningRate(0.025) //
                .minLearningRate(1e-3) // learning rate decays wrt # words. floor learning
                .negativeSample(12) // sample size in sentence
                .iterate(iter) //
                .tokenizerFactory(tokenizer).build();
        vec.fit();

        WordVectorSerializer.writeWordVectors(vec, "vec.txt");
    }

    @Test
    public void testPlausibility() throws IOException {
        assertNotNull(vec);
    }

}
