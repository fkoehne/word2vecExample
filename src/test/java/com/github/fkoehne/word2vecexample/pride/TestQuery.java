/**
 * 
 */
package com.github.fkoehne.word2vecexample.pride;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.plot.BarnesHutTsne;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/** Evaluates a learned model (assumed to be saved to vec.txt). */
public class TestQuery {

    private static WordVectors vec = null;

    @BeforeClass
    public static void learn() throws IOException {
        vec = WordVectorSerializer.loadTxtVectors(new File("vec.txt"));
    }

    public void assertSimilar(final String a, final String b) {
        final double value = vec.similarity(a, b);
        assertTrue(a + " and " + b + " are not recognized as similar", Math.abs((value - 1)) < 0.1);
        System.out.println(a + " and " + b + " are recognized as similar (" + value + ")");
    }

    public void associate(final String startingPoint) {
        final Collection<String> similar = vec.wordsNearest("startingPoint", 5);
        System.out.println("Similar words to '" + startingPoint + "' : " + similar);
    }

    @Test
    public void testPlausibility() throws IOException {
        assertSimilar("number", "amount");

    }

    @Test
    @Ignore
    public void testVisualization() throws IOException {
        final BarnesHutTsne tsne = new BarnesHutTsne.Builder().setMaxIter(1000).stopLyingIteration(250)
                .learningRate(500).useAdaGrad(false).theta(0.5).setMomentum(0.5).normalize(true).usePca(false).build();
        vec.lookupTable().plotVocab(tsne);
    }

}
