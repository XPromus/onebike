<script lang="ts">
	import AddTextBox from "$lib/components/AddTextBox.svelte";
	import type { NationEditDto } from "$lib/types/client/nation.types";
    let { 
        targetNation = $bindable()
    }: {
        targetNation: NationEditDto
    } = $props();
</script>

<div class="flex flex-col p-5 space-y-2 w-full rounded-md bg-panel">
    <AddTextBox bind:value={targetNation.longName} labelText="Long Name" placeholder="Germany"/>
    <AddTextBox bind:value={targetNation.shortName} labelText="Short Name" placeholder="GER"/>
    <AddTextBox bind:value={targetNation.flagEmoji} labelText="Flag Emoji" placeholder="🇩🇪"/>
</div>
