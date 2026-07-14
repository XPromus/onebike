<script lang="ts">
	import type { NationEditDto } from "$lib/api/types/nations.d.ts";
	import AddTextBox from "./AddTextBox.svelte";

    let { 
        nationEdit = $bindable()
    }: {
        nationEdit: NationEditDto
    } = $props();
</script>

<div class="flex flex-col p-5 space-y-2 w-full rounded-md bg-panel">
    <AddTextBox bind:value={nationEdit.longName} labelText="Long Name" placeholder="Germany"/>
    <AddTextBox bind:value={nationEdit.shortName} labelText="Short Name" placeholder="GER"/>
    <AddTextBox bind:value={nationEdit.flagEmoji} labelText="Flag Emoji" placeholder="🇩🇪"/>
</div>
